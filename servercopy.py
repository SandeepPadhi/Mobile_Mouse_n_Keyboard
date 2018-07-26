
# first of all import the socket library
import socket               
from firebase import firebase
import pyautogui
import json
import time



pyautogui.FAILSAFE = False

previous=0
mobilexmin,mobileymin=17,178
mobilexmax,mobileymax=1256,465
pcxmin,pcymin=0,0
pcxmax,pcymax=1361,767

factorx=float(float((pcxmax-pcxmin))/float((mobilexmax-mobilexmin)))
factory=float(float((pcymax-pcymin))/float((mobileymax-mobileymin)))
print(pcxmax-pcxmin)
print(mobilexmax-mobilexmin)
print(pcymax-pcymin)
print(mobileymax-mobileymin)

print(factorx)
print(factory)


previousx=0
previousy=0




time.sleep(2)





# next create a socket object
s = socket.socket()         
print "Socket successfully created"
 
# reserve a port on your computer in our
# case it is 12345 but it can be anything
port = 5000             
 
# Next bind to the port
# we have not typed any ip in the ip field
# instead we have inputted an empty string
# this makes the server listen to requests
# coming from other computers on the network
s.bind(('', port))        
print "socket binded to %s" %(port)
 
# put the socket into listening mode
s.listen(5)     
print "socket is listening"           
 
# a forever loop until we interrupt it or


 

# an error occurs
i=0
while True:
    c, addr = s.accept()     
    print 'Got connection from', addr
   # Establish connection with client.
 
   # send a thank you message to the client.

 
   # Close the connection with the client
    #print(c.recv(1024))
    data=c.recv(1024)
    print(data)
    data=data.split(",")
    length_of_data=len(data)

    x,y,relx,rely,l_click,d_click,r_click,drag,copy,paste,write=data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10]

    print(x+","+y+","+relx+","+rely+","+l_click+","+d_click+","+r_click+","+drag+","+copy+","+paste+","+write)
    if(length_of_data==11):
	write=1
	message=da192.168.43.63ta[10]
	paste=0
	




    if(str(write)=="1"):
        if(False):
		pyautogui.hotkey('backspace')
		pyautogui.hotkey('backspace')
		pyautogui.hotkey('backspace')
		pyautogui.hotkey('backspace')
	        #Result=firem.put('01',"Write","0")
                

        else:
		#print(message)		
		pyautogui.typewrite(message, interval=0.1)
        
		
        continue

    elif(str(l_click)  =='1'):
	pyautogui.click(button='left')
        continue

    elif(str(r_click) =='1'):
    	pyautogui.click(button='right')
        continue


    elif(str(d_click) =='1'):
    	pyautogui.click()
        pyautogui.click()
        continue
      

    elif(str(copy) =='1'):
    	pyautogui.hotkey('ctrl', 'c')  # ctrl-c to copy
        continue

    elif(str(paste) =='1'):
        pyautogui.hotkey('ctrl', 'v')  # ctrl-c to copy
        continue


    
   
    recentx=str(y)
    #recentx=recentx.replace(" ", "")
    print(type(recentx))
    print(recentx)
    recentx=int(recentx)
    print(type(recentx))
    if recentx is None:
        recentx=previousx
	recentx=recentx+17
    
    recentx=int(recentx)
    recentx=recentx-17
    
   

    recenty=y
    if recenty is None:
	recenty=previous
        recenty=recenty+160
    recenty=int(recenty)
    recenty=recenty-160
    
    recentxput=recentx
    recentyput=recenty

    if(i!=0):
    	recentxput=previousx-recentx
    	recentyput=previousy-recenty
    
    
    
    #print(pyautogui.position())


    no=int(drag)

    if(str(drag) == "0"):
        print("RELX:"+str(relx)+", RELY :"+str(rely))
        # print("RELX:"+str(int(int(dic['relx'])*factorx))+", RELY :"+str(int(int(dic['relx'])*factorx)))
        
        if((recentx-previousx)==0 and (recenty-previousy)==0):
		print("ZERO VALUES")
		pass
	else:
		pyautogui.moveRel(int(int(relx)*factorx*0.5), int(int(rely)*factory*0.3), duration=0.3)
		print("Moving")
		#print("RELX:"+str(dic['relx'])+", RELY :"+str(dic['rely']))
		
    else:
	
	 if((recentx-previousx)==0 and (recenty-previousy)==0):
		print("ZERO VALUES")
		pass
	 else:
		pyautogui.dragRel(int(int(relx)*factorx*0.5), int(int(rely)*factory*0.3), duration=0.3,button='left')
		print("Moving")
		#print("RELX:"+str(dic['relx'])+", RELY :"+str(dic['rely']))

        #print(str(recentx*factorx)+","+str(recenty*factory))
    	#pyautogui.moveTo(int(recentx*factorx), int(recenty*factory), duration=0.3) 
    no=no+1
    #Result=firem.put('01','Drag',str(no))

    #pyautogui.moveRel(int(recentxput*factorx), int(recentyput*factory), duration=1) 

    
    '''
    if previous!=recentx:
	pass
        pyautogui.moveTo(recentx, recenty, duration=0) 
        print(recentx)
    previous=recentx
    '''
    
    previousx=recentx
    previousy=recenty





    c.send('Thank you for connecting')
    c.close()
    i=i+1


