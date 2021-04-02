# -*- coding: utf-8 -*-
"""
Created on Fri Feb 12 18:00:03 2016

@author: adelyn.yeoh
"""
import csv
import pandas
import numpy as np
import matplotlib.pyplot as plt

"""
Helper functions here
"""

# Function to extract y values 
# Helpful in plotting 
def extract_yval(thislist):
    holdlist = []
    for i in range(0, len(thislist)):
        holdlist.append(thislist[i][1])
    return holdlist


"""
Begin sequence here 
"""

# Read file
readfile = open("data.csv")
datahold = []

for row in csv.reader(readfile):
    datahold.append(row)

names = datahold[0]

readfile = pandas.read_csv("data.csv")

df = pandas.DataFrame(data=readfile)

cols = []
for i in range(1, len(names)):
    cols.append(df[names[i]])

corrs = []
corr = np.corrcoef(df[names[2]], df[names[9]])

# Compute correlation here
for i in range(0, len(cols)):
    inter = []
    for j in range(0, len(cols)):
        if(j <= i ):
            inter.append(0)
        else:
            # Saves as 2x2 matrix
            corr = np.corrcoef(df[names[i]], df[names[j]])
            # Only want [0][1] or [1][0] values            
            inter.append(corr[0][1]) 
    corrs.append(inter)

# Save in data frame for nice printing            
df2 = pandas.DataFrame(corrs)
print(df2)


# Find correlations that are bigger than a certain
# tolerance 
corrpairs = []
for i in range(0, len(cols)):
    for j in range(0, len(cols)):
        if(corrs[i][j] > 0.05): # set tolerance here
            corrpairs.append(["(" + str(names[i]) + ", " + str(names[j]) 
            + ") ", corrs[i][j]]) # Formatting sake
            

# Print information nicely
for i in range(0, len(corrpairs)):
    print(corrpairs[i][0] + ': ' + str(corrpairs[i][1]))


# Make graph
index = np.arange(len(corrpairs))
plt.bar(index, extract_yval(corrpairs))
plt.ylabel('Correlations')
plt.xlabel('Pairs (sorted by index)')
plt.title('Correlations for pairs')
plt.tick_params(axis='x', which='both', bottom='off',top='off',labelbottom='off') 