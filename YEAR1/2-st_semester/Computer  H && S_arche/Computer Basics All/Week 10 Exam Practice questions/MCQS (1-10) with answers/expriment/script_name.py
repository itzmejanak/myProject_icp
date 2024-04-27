import re

def process_input_file(input_file, output_file):
    with open(input_file, 'r', encoding='utf-8', errors='replace') as file:
        lines = file.readlines()

    with open(output_file, 'w', encoding='utf-8') as file:
        for line in lines:
            if re.match(r'^\d+\.', line):
                file.write(line)

if __name__ == "__main__":
    input_file = 'input_file.txt'
    output_file = 'output_file.txt'
    process_input_file(input_file, output_file)
