# -*- coding: utf-8 -*-
"""
Created on Thu Feb 11 15:29:47 2016

@author: adelyn.yeoh
"""

import csv
import itertools as it

"""
Helper functions 
"""

# This function creates combination of a list of numbers/indices
def create_combi(thislist,  # Index of orders
                 length):   # Length of combination
    combilist = []
    for combi in it.combinations(thislist, length):
        hold = []
        i=0
        while(i < len(combi)):
           hold.append(combi[i])
           i = i+1
        combilist.append(hold)
    return combilist

# This function counts the number of occurrences 
# for a particular group size    
def countgroup(thislist, # List of combinations
               testdict, # Dictionary to hold information
               length):  # Length of combination
    for i in range(0, len(thislist)):
        name = str(thislist[i][0])
        for k in range(1, length):
            name = name + ", " + str(thislist[i][k])
        if(name not in testdict):
            testdict.update({name: 1})
        else:
            testdict[name] = testdict[name] + 1

# Save data in lists in the form I want to use
def getorders(datahold):
    mainlist = []
    for i in range(1, len(datahold)):
        rowhold = [datahold[i][0]]
        indexhold = []
        for j in range(1, len(names)):
            if(datahold[i][j] == '1'):  # save data when
                indexhold.append(j)     # an order has been made
        rowhold.append(indexhold)
        mainlist.append(rowhold)
    return(mainlist)

# Count the occurrences of each combination of each
# group of a particular length
def getcount(mainlist, grouplength):
    groupcounter = {}
    for i in range(1, len(datahold)-1):
        group = create_combi(mainlist[i][1], grouplength)
        countgroup(group, groupcounter, grouplength)
    return groupcounter


def getgroupinfo(counter, tolerance):
    grouplist = []
    for k in counter:
        if(counter[k] > tolerance):
            grouplist.append([ counter[k], k])
    grouplist.sort()
    grouplist.reverse()
    return grouplist
    
    
"""
Begin sequence here 
"""
readfile = open("data.csv")
datahold = []

for row in csv.reader(readfile):
    datahold.append(row)

names = datahold[0]


"""
Returns a list of lists:
ID and list of orders
"""

ordershold = getorders(datahold)


print('Group of two')
print(getgroupinfo(getcount(ordershold, 2), 2000))

print('\nGroup of three')
print(getgroupinfo(getcount(ordershold, 3), 2000))

print('\nGroup of four')
print(getgroupinfo(getcount(ordershold, 4), 200))

print('\nGroup of five')
print(getgroupinfo(getcount(ordershold, 5), 150))
print('\nGroup of six')
print(getgroupinfo(getcount(ordershold, 6), 20))

print('\nGroup of seven')
print(getgroupinfo(getcount(ordershold, 7), 5))

print('\nGroup of eight')
print(getgroupinfo(getcount(ordershold, 8), 1)) # not worth checking out


# largest order size is 12
