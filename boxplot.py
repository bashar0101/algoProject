import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns
import pandas as pd
file = pd.read_csv('graduation_rate.csv')
column = np.array(file['years to graduate'])
fig, ax = plt.subplots()
ax.boxplot(column)
ax.set_xticklabels(['Data'])
ax.set_ylabel('Number of years')
ax.set_title('Years ot gearduation')
plt.show()
