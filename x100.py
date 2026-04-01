import time
import pickle
import json
import toml
import re
from parser import TOMLParser
from serealization import dict_to_ron


def benchmark_full_manual():
    
    def full_cycle():
        with open('schedule.toml', 'r', encoding='utf-8') as f:
            toml_text = f.read()
        parser = TOMLParser()
        parser.parse(toml_text)
        dict_to_ron(parser.data)
        return 0
    
    return measure_time(full_cycle, "Полный цикл (ручной)")

def benchmark_full_lib():

    def full_cycle():
        with open('schedule.toml', 'r', encoding='utf-8') as f:
            data = toml.load(f)
        dict_to_ron(data)
        return 0
    
    return measure_time(full_cycle, "Полный цикл (библиотечный)")

def measure_time(func, description, iterations=100, ):
    
    
    start_time = time.perf_counter()
    for _ in range(iterations):
        func()
    end_time = time.perf_counter()
    
    elapsed = end_time - start_time
    time1 = elapsed
    

    return time1, elapsed

def analyze_performance(results):
    
    manual_full_time, _ = results['full_manual']
    lib_full_time, _ = results['full_lib']
    full_ratio = manual_full_time / lib_full_time if lib_full_time > 0 else 0
    
    print(f"\nПОЛНЫЙ ЦИКЛ (парсинг + сериализация):")
    print(f"   Ручной подход: {manual_full_time:.6f} сек за 100 итераций")
    print(f"   Библиотечный подход: {lib_full_time:.6f} сек за 100 итераций")
    if full_ratio < 1:
        print(f"   Ручной подход быстрее в {1/full_ratio:.2f} раз")
    else:
        print(f"   Библиотечный подход быстрее в {full_ratio:.2f} раз")

def main():
    open('schedule.toml', 'r', encoding='utf-8')
    
    open('parsed_data.bin', 'rb')
    
    results = {}

    results['full_manual'] = benchmark_full_manual()
    results['full_lib'] = benchmark_full_lib()
    
    analyze_performance(results)

if __name__ == "__main__":
    main()