//udp client
 import java.io.*;
 import java.net.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.awt.event.WindowEvent.*;

 class udpclient implements ActionListener
 {
  static Frame f;
  static TextField t1;
  static TextArea t2;
  static Button b;
  static PrintWriter out;
  static Socket socket;
  static String str;
  static ServerSocket ss;
  static DatagramSocket ds;
  static int buff_size=1024;
  static byte buff[]=new byte[buff_size];
  static DatagramPacket d1;
  static DatagramPacket d2;
  static InetAddress receiverAddr;
  static int receivePort;
  static String string=new String();

  udpclient()
   {
    
    f=new Frame("CLIENT CHAT");
    f.setSize(400,200);
    f.setLayout(new FlowLayout());
    t1= new TextField(20);
    t2=new TextArea(10,20);
     b=new Button("send");
     b.addActionListener(this);
     Panel p1=new Panel();
     p1.add(t1);
     p1.add(b);
     f.add(new Label("type messages here"));
     f.add(p1);
     f.add(t2);
     f.addWindowListener(new WindowAdapter()
 {
    public void  WindowClosing(WindowEvent we)
     { 

        System.exit(0);
       }
});

 f.setVisible(true);
 }

public void actionPerformed(ActionEvent e)
{
  try{
         str=t1.getText();
         buff=str.getBytes();
         d1=new DatagramPacket(buff,buff.length,receiverAddr,5670);
          ds.send(d1);
        string=string+"CLIENT:"+str+"\n";
         t2.setText(string);
           t1.setText(" ");
          }catch(Exception ek){}
}

 public static void main(String args[])throws IOException 
    {
      BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        receiverAddr=InetAddress.getByName("127.0.0.1");
         ds=new DatagramSocket();
         udpclient tp=new udpclient();
         String str1;
         while(true)
          {
             buff=new byte [buff_size];
             d2=new DatagramPacket(buff,buff.length);
              ds.receive(d2);
              str1=new String(d2.getData(),0,d2.getLength());
              receiverAddr=d2.getAddress();
              receivePort=d2.getPort();
               string=string+"SERVER:"+str1+"\n";
                 t2.setText(string);
          }
}
}
