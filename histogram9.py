import matplotlib.pyplot as plt
with open('results9.txt', 'r') as file:
    x_values = [int(line.strip()) for line in file]
y_values = ['selection sort','Insertion Sort','shell sort','Merge Sort','3-way Merge Sort','Quick Sort Lomuto','Quick Sort with Hoare','heap sort']
plt.plot(y_values, x_values, marker='o', linestyle='-', color='r')
plt.xlabel('Algorithm name')
plt.ylabel('Execution Time(milli seconds)')
plt.title('All Algorithms in put size (100000)')
plt.legend()
plt.show()
