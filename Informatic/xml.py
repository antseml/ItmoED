import pickle

def dict_to_xml(data, root_tag='расписание', indent=0):
    indent_str = ' ' * indent
    xml_lines = []
    
    if isinstance(data, dict):
        for key, val in data.items():
            if isinstance(val, (dict, list)):
                xml_lines.append(f'{indent_str}<{key}>')
                xml_lines.append(dict_to_xml(val, key, indent + 4))
                xml_lines.append(f'{indent_str}</{key}>')
            else:
                xml_lines.append(f'{indent_str}<{key}>{val}</{key}>')
        return '\n'.join(xml_lines)
    
    elif isinstance(data, list):
        for item in data:
            xml_lines.append(dict_to_xml(item, 'занятие', indent))
        return '\n'.join(xml_lines)
    
    else:
        return f'{indent_str}{data}'

def main():
    with open('parsed_data.bin', 'rb') as f:
        data = pickle.load(f)
    
    xml_body = dict_to_xml(data)
    xml_text = '<?xml version="1.0" encoding="UTF-8"?>\n' + xml_body
    
    with open('schedule.xml', 'w', encoding='utf-8') as f:
        f.write(xml_text)
    
    print(xml_text)

if __name__ == "__main__":
    main()