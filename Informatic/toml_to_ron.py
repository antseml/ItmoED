import toml
import json
from serealization import dict_to_ron

with open('schedule.toml', 'r', encoding='utf-8') as f:
    data = toml.load(f)

ron_text = dict_to_ron(data)

with open('schedule_lib.ron', 'w', encoding='utf-8') as f:
    f.write(ron_text)

print(ron_text)