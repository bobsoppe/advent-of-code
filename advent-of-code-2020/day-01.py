def solve_part_1(numbers):
    number_count = len(numbers)

    for i in range(number_count):
        for j in range(i + 1, number_count):
            if numbers[i] + numbers[j] == 2020:
                return numbers[i] * numbers[j]


def solve_part_2(numbers):
    number_count = len(numbers)

    for i in range(number_count):
        for j in range(i + 1, number_count):
            for k in range(j + 1, number_count):
                if numbers[i] + numbers[j] + numbers[k] == 2020:
                    return numbers[i] * numbers[j] * numbers[k]


data = list(map(int, open("day-01.txt", "r").read().splitlines()))

print("Part 1:", solve_part_1(data))
print("Part 2:", solve_part_2(data))
