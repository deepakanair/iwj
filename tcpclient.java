import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class tcpclient implements ActionListener
{
static Frame f;
static TextField tf;
static TextArea ta;
static Button b;
static PrintWriter out;
static Socket socket;
  static  String str,string="";
tcpclient()
{

f=new Frame("tcpc");
f.setLayout(new FlowLayout());
ta=new TextArea(10,30);
tf=new TextField(10);
b=new Button("send");
b.addActionListener(this);
f.add(tf);
f.add(b);
f.add(ta);
f.setSize(300,300);
f.setVisible(true);
   }
    
 public void actionPerformed(ActionEvent e)
    {
        try
        {str=tf.getText();
        out.println(str);
        string=string+"\nclient:"+str;
    
        ta.setText(string);
        tf.setText("");
        
        }catch(Exception ex)
        {
        }
    }
    
    
   public static void main(String args[]) throws IOException
                    {
                        tcpclient o=new tcpclient();

                       
                        socket=new Socket("127.0.0.1",4509);
                        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out=new PrintWriter(socket.getOutputStream(),true);
                                               while(true)
                        {
                            str=br.readLine();
                            string=string+"\nserver:"+str;
                          ta.setText(string);
                            

                                                             }
}
}

