import cv2
import statistics
import numpy as np
from matplotlib import pyplot as plt

hue = []
sal = []
val = []
value = []
hsv_mode = []
i = j = 0
count = 0
def findmode(list):
    set(list)
    return(max(set(list), key = list.count))
cap = cv2.VideoCapture(0)
background = cv2.imread("background.jpg")
#cv2.namedWindow('Frame')
while(i < 12):
    k = cv2.waitKey(10) & 0xFF
    _, frameinv = cap.read()
    frame = cv2.flip( frameinv, 1)
    #if(i == 11):
    frame1 = frame - background
    #cv2.imshow("subtracted", frame1)
    #cv2.imwrite('subtracted1.jpg',frame1)
    hsv = cv2.cvtColor( frame1, cv2.COLOR_BGR2HSV)
    #hsv = cv2.fastNlMeansDenoisingColored(hsv,None,10,10,7,21)
    cropped = hsv[450:480, 550:600]
    #cv2.imshow("cropped", hsv)
    #print cropped[2][3]
    #print cropped.shape[1]
    #while(i < cropped.shape[0]):
     #   while(j < cropped.shape[1]):
            #value.append(cropped[i][j])
           # print value
            #hue.append(value[0])
           # print hue
           # sal.append(value[1])
           # print sal
            #val.append(value[2])
            #print cropped[i][j]           
            #j+=1
        #del value[:]
        #i+=1'''
    cv2.imshow("frame", frame)
    #i+=1
    if cv2.waitKey(1) & 0xFF == ord('q'):
        cv2.destroyWindow('frame')
        break

#print(hsv.shape[0],hsv.shape[1])
while(i < len(cropped)):
    for x in cropped[i]:
        hue.append(x[0])
        sal.append(x[1])
        val.append(x[2])    
    i+=1

print('hue=',hue)
print('sal=',sal)
print('val=',val)
hsv_mode.append(findmode(hue))
hsv_mode.append(findmode(sal))
hsv_mode.append(findmode(val))
print hsv_mode
cap.release()
cv2.destroyAllWindows()
#3820149
#2969201

