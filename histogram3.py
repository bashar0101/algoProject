import matplotlib.pyplot as plt
with open('results3.txt', 'r') as file:
    x_values = [int(line.strip()) for line in file]
y_values = [100,1000,5000,10000,50000,100000]
plt.plot(y_values, x_values, marker='o', linestyle='-', color='r')
plt.xlabel('Input size')
plt.ylabel('Execution Time(milli seconds)')
plt.title('Shell sort')
plt.legend()
plt.show()
