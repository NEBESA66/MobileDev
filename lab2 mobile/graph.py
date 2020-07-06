import matplotlib.pyplot as plt
f = open('forgraph.txt')
xlist = []
ylist = []
for line in f:
    xlist.append(int((line.rstrip()).split(',')[0]))
    ylist.append(int((line.rstrip()).split(',')[1]))
plt.plot(xlist, ylist)
plt.show()