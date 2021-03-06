# -*- coding: utf-8 -*-
"""
Created on Sun Feb 14 23:24:22 2016

@author: adelyn.yeoh
"""


import matplotlib.pyplot as plt
import numpy as np


# Printed data
two = [[24328, '3, 8'], [24306, '8, 30'], [24266, '3, 30'], [24201, '6, 23'], [24173, '4, 6'], [24151, '4, 23'], [20288, '40, 43'], [20287, '2, 43'], [20281, '2, 40'], [20247, '36, 40'], [20245, '2, 36'], [20231, '36, 43'], [20228, '10, 40'], [20224, '2, 10'], [20183, '10, 43'], [20152, '10, 36']]
three = [[21767, '3, 8, 30'], [21670, '4, 6, 23'], [18235, '2, 40, 43'], [18207, '36, 40, 43'], [18186, '2, 36, 40'], [18179, '2, 36, 43'], [18177, '2, 10, 40'], [18173, '10, 40, 43'], [18163, '2, 10, 43'], [18138, '10, 36, 40'], [18108, '2, 10, 36'], [18099, '10, 36, 43']]
four = [[16374, '2, 36, 40, 43'], [16374, '2, 10, 40, 43'], [16332, '10, 36, 40, 43'], [16314, '2, 10, 36, 40'], [16293, '2, 10, 36, 43'], [253, '3, 8, 23, 30'], [248, '3, 8, 10, 30'], [245, '3, 8, 30, 44'], [242, '4, 6, 17, 23'], [240, '4, 5, 6, 23'], [239, '4, 6, 18, 23'], [238, '3, 8, 30, 47'], [237, '3, 8, 30, 43'], [234, '4, 6, 19, 23'], [233, '4, 6, 20, 23'], [233, '4, 6, 15, 23'], [233, '3, 8, 30, 46'], [232, '4, 6, 8, 23'], [232, '4, 6, 23, 50'], [232, '3, 8, 30, 41'], [231, '4, 6, 14, 23'], [230, '3, 8, 30, 48'], [227, '4, 6, 23, 38'], [227, '4, 6, 23, 33'], [226, '4, 6, 23, 48'], [226, '3, 8, 29, 30'], [226, '3, 8, 18, 30'], [225, '4, 6, 12, 23'], [225, '3, 8, 28, 30'], [225, '3, 5, 8, 30'], [224, '4, 6, 23, 39'], [224, '4, 6, 23, 25'], [224, '4, 6, 21, 23'], [224, '4, 6, 16, 23'], [224, '3, 8, 30, 38'], [223, '4, 6, 7, 23'], [223, '4, 6, 23, 46'], [223, '3, 8, 30, 49'], [223, '3, 8, 30, 42'], [223, '3, 7, 8, 30'], [222, '4, 6, 23, 28'], [222, '4, 6, 22, 23'], [221, '4, 6, 23, 49'], [221, '4, 6, 23, 42'], [221, '3, 8, 30, 40'], [221, '3, 8, 20, 30'], [221, '3, 4, 6, 23'], [220, '4, 6, 23, 47'], [219, '4, 6, 23, 35'], [219, '3, 8, 9, 30'], [218, '3, 8, 30, 37'], [217, '4, 6, 23, 41'], [217, '2, 3, 8, 30'], [216, '4, 6, 23, 43'], [216, '3, 8, 14, 30'], [215, '4, 6, 13, 23'], [215, '3, 8, 30, 34'], [215, '1, 2, 36, 40'], [214, '4, 6, 23, 32'], [214, '4, 6, 23, 24'], [214, '4, 6, 11, 23'], [214, '3, 8, 30, 33'], [214, '3, 6, 8, 30'], [214, '1, 2, 36, 43'], [214, '1, 2, 10, 40'], [214, '1, 2, 10, 36'], [213, '4, 6, 9, 23'], [213, '3, 8, 30, 50'], [213, '3, 8, 30, 31'], [213, '3, 8, 13, 30'], [213, '2, 6, 10, 43'], [213, '2, 30, 36, 40'], [212, '3, 8, 30, 36'], [212, '3, 8, 30, 32'], [212, '3, 8, 24, 30'], [212, '3, 8, 12, 30'], [211, '2, 30, 36, 43'], [211, '2, 10, 30, 36'], [211, '1, 2, 40, 43'], [211, '1, 2, 10, 43'], [210, '3, 8, 15, 30'], [210, '3, 8, 11, 30'], [210, '2, 10, 30, 40'], [209, '4, 6, 23, 34'], [209, '4, 6, 23, 27'], [209, '2, 30, 40, 43'], [209, '10, 21, 40, 43'], [208, '4, 6, 23, 36'], [208, '3, 8, 30, 35'], [208, '3, 4, 8, 30'], [208, '2, 6, 36, 43'], [208, '1, 4, 6, 23'], [208, '1, 10, 36, 40'], [207, '2, 39, 40, 43'], [207, '2, 10, 30, 43'], [206, '2, 10, 21, 43'], [206, '10, 30, 36, 40'], [205, '4, 6, 23, 26'], [205, '4, 6, 10, 23'], [205, '2, 36, 43, 46'], [205, '2, 10, 21, 40'], [205, '10, 11, 36, 43'], [205, '1, 36, 40, 43'], [204, '1, 10, 36, 43'], [203, '30, 36, 40, 43'], [203, '3, 8, 27, 30'], [203, '3, 8, 25, 30'], [203, '3, 8, 19, 30'], [203, '3, 8, 17, 30'], [203, '2, 36, 39, 43'], [203, '2, 10, 43, 46'], [203, '10, 30, 36, 43'], [203, '1, 3, 8, 30'], [203, '1, 10, 40, 43'], [202, '3, 8, 22, 30'], [202, '3, 10, 36, 43'], [202, '2, 6, 10, 36'], [202, '2, 36, 40, 45'], [202, '2, 14, 36, 40'], [202, '2, 10, 40, 45'], [202, '2, 10, 14, 36'], [202, '2, 10, 11, 36'], [201, '6, 10, 36, 43'], [201, '2, 6, 10, 40'], [201, '2, 40, 43, 49'], [201, '2, 4, 6, 23'], [201, '2, 21, 40, 43'], [201, '2, 11, 36, 43'], [201, '2, 10, 39, 43'], [201, '2, 10, 36, 46'], [201, '2, 10, 11, 43'], [201, '10, 14, 36, 40']]
five = [[14692, '2, 10, 36, 40, 43'], [194, '1, 2, 36, 40, 43'], [194, '1, 2, 10, 36, 40'], [193, '1, 2, 10, 36, 43'], [192, '2, 10, 30, 36, 40'], [191, '2, 30, 36, 40, 43'], [191, '1, 2, 10, 40, 43'], [189, '2, 10, 30, 36, 43'], [189, '2, 10, 21, 40, 43'], [188, '2, 10, 30, 40, 43'], [187, '2, 6, 10, 36, 43'], [186, '2, 10, 39, 40, 43'], [185, '2, 36, 39, 40, 43'], [185, '1, 10, 36, 40, 43'], [184, '2, 10, 14, 36, 40'], [184, '2, 10, 11, 36, 43'], [183, '2, 6, 10, 40, 43'], [183, '2, 10, 36, 43, 46'], [183, '2, 10, 36, 39, 43'], [182, '10, 30, 36, 40, 43'], [181, '2, 10, 25, 36, 40'], [181, '2, 10, 21, 36, 43'], [181, '10, 21, 36, 40, 43'], [180, '2, 14, 36, 40, 43'], [180, '2, 10, 36, 40, 45'], [180, '10, 36, 39, 40, 43'], [179, '2, 10, 40, 43, 49'], [179, '2, 10, 36, 39, 40'], [179, '2, 10, 14, 36, 43'], [179, '2, 10, 11, 40, 43'], [179, '2, 10, 11, 36, 40'], [179, '10, 14, 36, 40, 43'], [179, '10, 11, 36, 40, 43'], [178, '3, 10, 36, 40, 43'], [178, '2, 10, 21, 36, 40'], [178, '10, 27, 36, 40, 43'], [177, '2, 36, 40, 43, 45'], [177, '2, 19, 36, 40, 43'], [177, '2, 10, 25, 40, 43'], [176, '2, 6, 36, 40, 43'], [176, '2, 36, 40, 43, 49'], [176, '2, 36, 40, 43, 46'], [176, '2, 3, 10, 36, 43'], [176, '2, 21, 36, 40, 43'], [176, '2, 11, 36, 40, 43'], [176, '2, 10, 40, 43, 45'], [176, '2, 10, 36, 43, 45'], [176, '2, 10, 27, 36, 43'], [176, '2, 10, 19, 36, 40'], [175, '2, 10, 25, 36, 43'], [175, '2, 10, 14, 40, 43'], [175, '10, 19, 36, 40, 43'], [174, '2, 6, 10, 36, 40'], [174, '2, 10, 40, 43, 46'], [174, '2, 10, 27, 36, 40'], [174, '2, 10, 19, 40, 43'], [174, '10, 25, 36, 40, 43'], [173, '6, 10, 36, 40, 43'], [173, '2, 27, 36, 40, 43'], [173, '10, 36, 40, 43, 49'], [173, '10, 36, 40, 43, 45'], [172, '2, 3, 10, 40, 43'], [172, '2, 26, 36, 40, 43'], [172, '2, 10, 36, 40, 46'], [172, '2, 10, 26, 36, 43'], [171, '2, 35, 36, 40, 43'], [171, '2, 25, 36, 40, 43'], [171, '2, 10, 27, 40, 43'], [171, '2, 10, 26, 40, 43'], [170, '2, 36, 40, 43, 47'], [170, '2, 10, 36, 40, 49'], [170, '2, 10, 35, 40, 43'], [170, '2, 10, 19, 36, 43'], [169, '2, 20, 36, 40, 43'], [169, '2, 18, 36, 40, 43'], [169, '2, 10, 28, 36, 40'], [169, '10, 36, 40, 43, 46'], [168, '2, 3, 36, 40, 43'], [168, '2, 28, 36, 40, 43'], [168, '2, 10, 40, 43, 47'], [168, '2, 10, 20, 36, 40'], [168, '2, 10, 12, 40, 43'], [168, '10, 36, 40, 43, 47'], [168, '10, 28, 36, 40, 43'], [167, '2, 32, 36, 40, 43'], [167, '2, 13, 36, 40, 43'], [167, '2, 10, 36, 43, 49'], [167, '2, 10, 13, 40, 43'], [167, '2, 10, 12, 36, 43'], [166, '2, 10, 36, 38, 43'], [166, '2, 10, 35, 36, 43'], [166, '2, 10, 28, 36, 43'], [166, '2, 10, 20, 40, 43'], [166, '2, 10, 18, 40, 43'], [166, '2, 10, 18, 36, 40'], [166, '2, 10, 13, 36, 40'], [166, '10, 33, 36, 40, 43'], [166, '10, 26, 36, 40, 43'], [165, '2, 12, 36, 40, 43'], [165, '2, 10, 36, 40, 41'], [165, '2, 10, 33, 40, 43'], [165, '2, 10, 26, 36, 40'], [165, '2, 10, 20, 36, 43'], [165, '10, 20, 36, 40, 43'], [165, '10, 13, 36, 40, 43'], [164, '2, 33, 36, 40, 43'], [164, '2, 10, 28, 40, 43'], [164, '10, 35, 36, 40, 43'], [162, '2, 10, 35, 36, 40'], [162, '2, 10, 33, 36, 43'], [162, '2, 10, 24, 40, 43'], [161, '2, 36, 40, 41, 43'], [161, '2, 10, 36, 43, 47'], [161, '2, 10, 32, 40, 43'], [161, '2, 10, 32, 36, 40'], [161, '10, 36, 40, 41, 43'], [161, '10, 36, 38, 40, 43'], [161, '10, 18, 36, 40, 43'], [160, '2, 8, 10, 40, 43'], [160, '2, 15, 36, 40, 43'], [160, '2, 10, 33, 36, 40'], [160, '2, 10, 32, 36, 43'], [160, '2, 10, 13, 36, 43'], [159, '2, 7, 10, 36, 40'], [159, '2, 3, 10, 36, 40'], [159, '2, 10, 36, 41, 43'], [159, '2, 10, 36, 40, 47'], [159, '2, 10, 18, 36, 43'], [159, '2, 10, 16, 36, 43'], [159, '10, 16, 36, 40, 43'], [158, '2, 7, 10, 36, 43'], [158, '2, 36, 38, 40, 43'], [158, '2, 16, 36, 40, 43'], [158, '2, 10, 16, 40, 43'], [157, '2, 9, 10, 40, 43'], [157, '2, 10, 40, 43, 48'], [157, '2, 10, 36, 40, 48'], [157, '2, 10, 12, 36, 40'], [157, '10, 36, 40, 43, 48'], [157, '10, 17, 36, 40, 43'], [156, '2, 8, 10, 36, 40'], [156, '2, 10, 40, 42, 43'], [156, '2, 10, 38, 40, 43'], [156, '2, 10, 36, 38, 40'], [156, '10, 36, 40, 42, 43'], [155, '2, 7, 36, 40, 43'], [155, '2, 4, 36, 40, 43'], [155, '2, 24, 36, 40, 43'], [155, '2, 10, 36, 43, 48'], [155, '10, 32, 36, 40, 43'], [155, '10, 24, 36, 40, 43'], [155, '10, 12, 36, 40, 43'], [154, '2, 36, 40, 43, 48'], [154, '2, 10, 40, 41, 43'], [154, '2, 10, 36, 40, 42'], [154, '2, 10, 16, 36, 40'], [154, '2, 10, 15, 36, 43'], [153, '2, 10, 24, 36, 43'], [152, '7, 10, 36, 40, 43'], [152, '2, 9, 10, 36, 43'], [152, '2, 7, 10, 40, 43'], [152, '2, 10, 29, 36, 40'], [152, '2, 10, 17, 36, 43'], [151, '9, 10, 36, 40, 43'], [151, '2, 8, 36, 40, 43'], [151, '2, 10, 29, 40, 43'], [151, '2, 10, 24, 36, 40'], [151, '2, 10, 15, 40, 43'], [151, '10, 15, 36, 40, 43']]
six = [[175, '1, 2, 10, 36, 40, 43'], [171, '2, 10, 30, 36, 40, 43'], [168, '2, 10, 36, 39, 40, 43'], [165, '2, 10, 21, 36, 40, 43'], [163, '2, 10, 14, 36, 40, 43'], [163, '2, 10, 11, 36, 40, 43'], [160, '2, 6, 10, 36, 40, 43'], [159, '2, 10, 25, 36, 40, 43'], [159, '2, 10, 19, 36, 40, 43'], [158, '2, 10, 36, 40, 43, 45'], [158, '2, 10, 27, 36, 40, 43'], [157, '2, 10, 36, 40, 43, 46'], [155, '2, 3, 10, 36, 40, 43'], [155, '2, 10, 36, 40, 43, 49'], [152, '2, 10, 35, 36, 40, 43'], [151, '2, 10, 28, 36, 40, 43'], [150, '2, 10, 26, 36, 40, 43'], [149, '2, 10, 36, 40, 43, 47'], [149, '2, 10, 20, 36, 40, 43'], [148, '2, 10, 13, 36, 40, 43'], [147, '2, 10, 33, 36, 40, 43'], [147, '2, 10, 18, 36, 40, 43'], [146, '2, 10, 12, 36, 40, 43'], [144, '2, 10, 36, 40, 41, 43'], [144, '2, 10, 16, 36, 40, 43'], [143, '2, 10, 36, 38, 40, 43'], [141, '2, 7, 10, 36, 40, 43'], [141, '2, 10, 36, 40, 43, 48'], [139, '2, 10, 24, 36, 40, 43'], [139, '2, 10, 15, 36, 40, 43'], [138, '2, 10, 36, 40, 42, 43'], [138, '2, 10, 32, 36, 40, 43'], [136, '2, 8, 10, 36, 40, 43'], [136, '2, 10, 17, 36, 40, 43'], [135, '2, 9, 10, 36, 40, 43'], [134, '2, 4, 10, 36, 40, 43'], [132, '2, 10, 34, 36, 40, 43'], [131, '2, 10, 29, 36, 40, 43'], [130, '2, 5, 10, 36, 40, 43'], [130, '2, 10, 23, 36, 40, 43'], [127, '2, 10, 36, 40, 43, 44'], [124, '2, 10, 31, 36, 40, 43'], [124, '2, 10, 22, 36, 40, 43'], [118, '2, 10, 36, 40, 43, 50'], [116, '2, 10, 36, 37, 40, 43']]
names =['id', 'item_0', 'item_1', 'item_2', 'item_3', 'item_4', 'item_5', 'item_6', 'item_7', 'item_8', 'item_9', 'item_10', 'item_11', 'item_12', 'item_13', 'item_14', 'item_15', 'item_16', 'item_17', 'item_18', 'item_19', 'item_20', 'item_21', 'item_22', 'item_23', 'item_24', 'item_25', 'item_26', 'item_27', 'item_28', 'item_29', 'item_30', 'item_31', 'item_32', 'item_33', 'item_34', 'item_35', 'item_36', 'item_37', 'item_38', 'item_39', 'item_40', 'item_41', 'item_42', 'item_43', 'item_44', 'item_45', 'item_46', 'item_47', 'item_48', 'item_49']


"""
Helper functions
"""
def extract_yval(thislist):
    holdlist = []
    for i in range(0, len(thislist)):
        holdlist.append(thislist[i][0])
    return holdlist

def extract_xnames(thislist):
    holdlist = []
    for i in range(0, len(thislist)):
        holdlist.append(thislist[i][1])
    return holdlist

def makenicelist(thislist):
    nicelist = []    
    for i in range(0, len(thislist)):
        thisstr = thislist[i][1]
        val = ""        
        hold = []
        for k in range(0, len(thisstr)):
            if(thisstr[k]==','):
                hold.append(int(val))
                val = ""
            elif(thisstr[k] == ' '):
                val = ""
            else:
                val = val + thisstr[k]
        hold.append(val)
        nicelist.append(hold)
    return nicelist
            
def printitemnames(nicelist, mainlist):
    for i in range(0, len(nicelist)):
        thisstr = str(names[nicelist[i][0]])        
        for k in range(1, len(nicelist[i])):
            thisstr = thisstr + ', ' + names[int(nicelist[i][k])]
        print('(' + thisstr + '):' + str(mainlist[i][0]))
        
def make_graph(thislist):
    index = np.arange(len(thislist))
    bar_width = 0.5
    plt.bar(index, extract_yval(thislist))
    plt.xticks(index + bar_width, extract_xnames(thislist))
    plt.ylabel('Frequency')
    plt.xlabel('Groups')
    plt.title('Distribution of frequency of item groups of six')
    plt.tick_params(axis='x', which='both', bottom='off',top='off',
                    labelbottom='off') 
                    
                    
# Make graphs
make_graph(two)
make_graph(three)
make_graph(four)
make_graph(five)
make_graph(six)

printitemnames(makenicelist(three), three)
