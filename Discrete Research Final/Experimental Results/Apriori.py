import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

dataset = pd.read_csv('PythonCSV5.csv', error_bad_lines=False, header = None,encoding = "ANSI", engine = "python")
transactions = []
for i in range(0, 5000):
    print(i)
    transactions.append([str(dataset.values[i,j]) for j in range(0, 20)])
print("Done")

from apyori import apriori
rules = apriori(transactions, min_support = 0.010, min_confidence = 0.25, min_lift = 5, min_length = 5)
results = list(rules)
print(results)
print("Done 2")