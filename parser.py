import pickle

class TOMLParser:
    def __init__(self):
        self.data = {}
        self.cur = self.data
    
    def parse(self, text):
        for line in text.split('\n'):
            line = line.strip()
            if not line or line[0] == '#':
                continue
            if line.startswith('[['):
                path = line[2:-2].strip().replace('"', '').replace("'", "")
                parts = path.split('.')
                
                self.cur = self.data
                for part in parts[:-1]:
                    if part not in self.cur:
                        self.cur[part] = {}
                    self.cur = self.cur[part]
                
                last = parts[-1]
                if last not in self.cur:
                    self.cur[last] = []
                self.cur[last].append({})
                self.cur = self.cur[last][-1]
            
            elif '=' in line:
                key, value = line.split('=', 1)
                key = key.strip().strip('"\'')
                value = value.strip().strip('"\'')
                self.cur[key] = value

def main():
    with open('schedule.toml', 'r', encoding='utf-8') as f:
        parser = TOMLParser()
        parser.parse(f.read())
            
        with open('parsed_data.bin', 'wb') as f2:
            pickle.dump(parser.data, f2)
            
        print(parser.data)
            
if __name__ == "__main__":
    main()