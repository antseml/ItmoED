import pickle


def dict_to_ron(data, indent=0):
    sp = ' ' * indent
    if isinstance(data, dict):
        lines = []
        for k, v in data.items():
            key = f'"{k}"' if not str(k).replace('_', '') else k
            lines.append(f'{sp}    {key}: {dict_to_ron(v, indent+4)}')
        return '(\n' + ',\n'.join(lines) + f'\n{sp})'
    elif isinstance(data, list):
        lines = [f'{sp}    {dict_to_ron(item, indent+4)}' for item in data]
        return '[\n' + ',\n'.join(lines) + f'\n{sp}]'
    elif isinstance(data, str):
        return f'"{data.replace(chr(34), chr(92)+chr(34))}"'

def main():
    with open('parsed_data.bin', 'rb') as f:
        data = pickle.load(f)
    to_r = dict_to_ron(data)
    with open('schedule.ron', 'w', encoding='utf-8') as f:
        f.write(to_r)
    print(to_r)

if __name__ == "__main__":
    main()