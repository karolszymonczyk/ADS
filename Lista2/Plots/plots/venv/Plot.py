from matplotlib import pyplot as plt
from matplotlib import style
import math
import pathlib
import numpy as np

style.use('ggplot')

K = 2000
current_dir = pathlib.Path(__file__).parent
dest = current_dir/'stats'

nS, cS, sS, tS = np.loadtxt(dest/'statsSelect',
                        unpack = True,
                        delimiter = ';')

nI, cI, sI, tI = np.loadtxt(dest/'statsInsert',
                        unpack = True,
                        delimiter = ';')

nQ, cQ, sQ, tQ = np.loadtxt(dest/'statsQuick',
                        unpack = True,
                        delimiter = ';')

nH, cH, sH, tH = np.loadtxt(dest/'statsHeap',
                        unpack = True,
                        delimiter = ';')

nM, cM, sM, tM = np.loadtxt(dest/'statsModifiedQuick',
                        unpack = True,
                        delimiter = ';')

avN = []
avTS, avTI, avTQ, avTH, avTM = ([] for i in range(5))
avSS, avSI, avSQ, avSH, avSM = ([] for i in range(5))
avCS, avCI, avCQ, avCH, avCM = ([] for i in range(5))

def averageSum(numList, n) :
    return np.sum(numList[n:n+K])/K

def appendTime(n) :
    avTS.append(averageSum(tS,n))
    avTI.append(averageSum(tI,n))
    avTQ.append(averageSum(tQ,n))
    avTH.append(averageSum(tH,n))
    avTM.append(averageSum(tM,n))

def appendSwaps(n) :
    avSS.append(averageSum(sS,n))
    avSI.append(averageSum(sI,n))
    avSQ.append(averageSum(sQ,n))
    avSH.append(averageSum(sH,n))
    avSM.append(averageSum(sM,n))

def appendComparisions(n) :
    avCS.append(averageSum(cS,n))
    avCI.append(averageSum(cI,n))
    avCQ.append(averageSum(cQ,n))
    avCH.append(averageSum(cH,n))
    avCM.append(averageSum(cM,n))

n = 0

for i in range(100) :
    avN.append(nS[i*K])
    appendTime(n)
    appendSwaps(n)
    appendComparisions(n)
    n+=K

avN = np.array(avN)

avCS = np.array(avCS)
avCI = np.array(avCI)
avCQ = np.array(avCQ)
avCH = np.array(avCH)
avCM = np.array(avCM)

avSS = np.array(avSS)
avSI = np.array(avSI)
avSQ = np.array(avSQ)
avSH = np.array(avSH)
avSM = np.array(avSM)

def setPlot(title, xlabel, ylabel, avS, avI, avQ, avH, avM) :
    fig = plt.figure(figsize=(12,8))
    plt.xlabel(xlabel, fontsize=20)
    plt.ylabel(ylabel, fontsize=20)
    plt.rc('xtick', labelsize=15)
    plt.rc('ytick', labelsize=15)
    plt.ticklabel_format(style='plain')
    plt.plot(avN, (avS), label='Select')
    plt.plot(avN, (avI), label='Insert')
    plt.plot(avN, (avQ), label='Quick')
    plt.plot(avN, (avH), label='Heap')
    plt.plot(avN, (avM), label='Modified')
    plt.subplots_adjust(left=0.15, bottom=0.1, right=1, top=1)
    plt.legend()
    plt.savefig('./plots/'+title+'.png')

def setLogPlot(title, xlabel, ylabel, avS, avI, avQ, avH, avM):
    fig = plt.figure(figsize=(12,8))
    plt.xlabel(xlabel, fontsize=20)
    plt.ylabel(ylabel, fontsize=20)
    plt.rc('xtick', labelsize=15)
    plt.rc('ytick', labelsize=15)
    plt.yscale('log')
    plt.plot(avN, (avS), label='Select')
    plt.plot(avN, (avI), label='Insert')
    plt.plot(avN, (avQ), label='Quick')
    plt.plot(avN, (avH), label='Heap')
    plt.plot(avN, (avM), label='Modified')
    plt.subplots_adjust(left=0.15, bottom=0.1, right=1, top=1)
    plt.legend()
    plt.savefig('./plots/'+title+'.png')


setPlot('t', 'elements', 'time [nanoseconds]', avTS, avTI, avTQ, avTH, avTM)
setPlot('s', 'elements', 'swaps', avSS, avSI, avSQ, avSH, avSM)
setPlot('c', 'elements', 'comparisons', avCS, avCI, avCQ, avCH, avCM)
setPlot('cn', 'elements', 'comparisons / elements', avCS/avN, avCI/avN, avCQ/avN, avCH/avN, avCM/avN)
setPlot('sn', 'elements', 'swaps / elements', avSS/avN, avSI/avN, avSQ/avN, avSH/avN, avSM/avN)

setLogPlot('lt', 'elements', 'log ( time ) [nanoseconds]', avTS, avTI, avTQ, avTH, avTM)
setLogPlot('ls', 'elements', 'log ( swaps )', avSS, avSI, avSQ, avSH, avSM)
setLogPlot('lc', 'elements', 'log ( comparison )', avCS, avCI, avCQ, avCH, avCM)
setLogPlot('lcn', 'elements', 'log ( comparisons / elements )', avCS/avN, avCI/avN, avCQ/avN, avCH/avN, avCM/avN)
setLogPlot('lsn', 'elements', 'log ( swaps / elements )', avSS/avN, avSI/avN, avSQ/avN, avSH/avN, avSM/avN)

plt.show()