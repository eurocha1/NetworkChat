package chat_client;

import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class client_frame extends javax.swing.JFrame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    
    public static BigInteger p, q;

    
    private JTextField pVal = new JTextField();
	private JTextField pVal2 = new JTextField();
	private JTextField qVal2 = new JTextField();
	private JTextField qVal = new JTextField();

    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    
    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data, String data2) 
    {
        users.add(data);
        clientList.setText("");
        
        for (String current_user : users)
        {
            clientList.append(current_user);
            clientList.append("\n");
        }    
    }
    
    //--------------------------//
    
    public void userRemove(String data, String data2) 
    {
         ta_chat.append(data + " is now offline.\n");
         clientList.setText("");
        
        for (String current_user : users)
        {
            clientList.append(current_user);
            clientList.append("\n");
        }    
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
    }
    
    //--------------------------//
    
    public void sendDisconnect(String name) 
    {
        String bye = (name + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }
    

   
    
    //-------------------------//
    
    public client_frame() 
    {
        initComponents();
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat", error = "Error", exist = "Exist";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");
                     
                     if (data[2].equals(chat)) 
                     {
                        String restoredMessage = data[1];
                        
                        if(restoredMessage.contains(";"))
                    	{
                            String[] d = data[1].split(";");

                            RSA rsa1 = new RSA();
                            // Decrypt the encrypted message

                            //BigInteger encrypted = new BigInteger(d[0]);
                            //System.out.println("trying to decrypt" + d[0]);
                            BigInteger dd = new BigInteger(d[1]);
                            BigInteger nn = new BigInteger(d[2]);
                            
                            //System.out.println("d: "+dd);
                            //System.out.println("n: "+nn);
                            String restored = "";
                            String text = "";
                            int l = d[0].length()/17;
                            int x = 0;
                            int i = 0;
                            BigInteger encrypted = null;
                            BigInteger decrypted = null;
                            while(i < l)
                            {
                            	text = d[0].substring(i*17, (i*17)+17);
                            	if(text.charAt(0) == '0')
                            	{
                            		text = text.substring(1, text.length());
                            	}
                            	encrypted = new BigInteger(text);
                            	decrypted = rsa1.decrypt(encrypted ,dd, nn);
                            	restored += rsa1.cipherToString(decrypted);
                            	text = "";
                            	i++;
                            }
                            

                            // Uncipher the decrypted message to text

                            
                     
                            restoredMessage = restored;
                        }
                        
                        ta_chat.append(data[0] + ": " + restoredMessage + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                        userAdd(data[0],data[3]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0],data[3]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                     else if(data[2].equals(error))
                     {
                         JOptionPane.showMessageDialog(client_frame.this,
			                  data[1]+" not found",
			                  "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                     }
                     else if(data[2].equals(exist))
                     {
                         JOptionPane.showMessageDialog(client_frame.this,
			                  "username already exist",
			                  "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                         sendDisconnect(data[0]);
                         Disconnect();
                     }
                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        tf_password = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        clientList = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_address.setText("Address : ");

        tf_address.setText("127.0.0.1");
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });

        lb_port.setText("Port :");

        lb_username.setText("Username :");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        clientList.setColumns(20);
        clientList.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        clientList.setRows(5);
        jScrollPane2.setViewportView(clientList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("List of Users");

        jMenu1.setText("File");

        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(helpMenuItem);

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(aboutMenuItem);

        jMenuBar2.add(jMenu1);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lb_username, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                    .addComponent(lb_address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lb_port, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_password))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(b_connect)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(b_disconnect)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_address)
                    .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_port))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_username)
                        .addComponent(b_connect)
                        .addComponent(b_disconnect)
                        .addComponent(tf_username)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_chat)
                    .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed
       
    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
    
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        if (isConnected == false) 
        {
            username = tf_username.getText();
            port = Integer.parseInt(tf_password.getText());
            tf_username.setEditable(false);
            
            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true;
        		Object[] message2 = {
					    "Please Choose your Prime number p and q\n"
						+"To Select Randomly, Please press No"	
						,
					    "Prime number p", pVal,
					    "Prime number q", qVal
					};
				int option2 = JOptionPane.showConfirmDialog(null, message2, "Prime number", JOptionPane.OK_OPTION,JOptionPane.NO_OPTION);
				
				
				
				
					
				
    				if(pVal.getText().equals("")  && qVal.getText().equals("") && option2 !=  JOptionPane.NO_OPTION)
    				{
        				p= BigInteger.ONE;
        				q =BigInteger.ONE;
    				}
    				else if(option2 ==  JOptionPane.NO_OPTION)
    				{
    			        p = RSA.largePrime(30);
    			        q = RSA.largePrime(30);
    				}
    				
    				else if(option2 == JOptionPane.OK_OPTION && !pVal.getText().equals("") && !qVal.getText().equals(""))
    				{
    					p = new BigInteger(pVal.getText());
        				q = new BigInteger(qVal.getText());

        			
    				
    				}
    				


    				

				BigInteger a = BigInteger.valueOf(128);
				BigInteger b = a.pow(8);
				BigInteger comp = (p.multiply(q));
				int res = comp.compareTo(b);

				System.out.println("p : " + p);
				System.out.println("q : " + q);
				
				if(option2 !=JOptionPane.NO_OPTION){
    				while ( res == -1 || isPrime(p) ==false || isPrime(q) ==false)
    				{
         				

    					Object[] message3 = {
        					    "Please Enter valid Prime number p and q (p*q should be larger than 128^8)",
        					    "Prime number p", pVal2,
        					    "Prime number q", qVal2
        					};
        				int option3 = JOptionPane.showConfirmDialog(null, message3, "Prime number", JOptionPane.OK_OPTION, JOptionPane.NO_OPTION);
        				
        				
        				if(pVal2.getText().equals("")  && qVal2.getText().equals("") && option3 !=  JOptionPane.NO_OPTION)
        				{
        					p= BigInteger.ONE;
            				q =BigInteger.ONE;
        				}
        				else if(option3 ==  JOptionPane.NO_OPTION)
        				{
        					p = new BigInteger(pVal2.getText());
            				q = new BigInteger(qVal2.getText());
        				}
        				
        				else if(option3 == JOptionPane.OK_OPTION && !pVal2.getText().equals("") && !qVal2.getText().equals(""))
        				{
        					p = new BigInteger(pVal2.getText());
            				q = new BigInteger(qVal2.getText());
        				}
        				
        				 a = BigInteger.valueOf(128);
        				 b = a.pow(8);
        				 comp = (p.multiply(q));
         				 res = comp.compareTo(b);
         			
        				System.out.println("p : " + p);
        				System.out.println("q : " + q);

    				}
				}

            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect(tf_username.getText());
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        
        RSA rsa = new RSA();
        
        // 1. Find 2 large primes p and q
        BigInteger p = BigInteger.valueOf(275604541);
        									
		BigInteger q = BigInteger.valueOf(370977499);

        // 2. Compute n from p and q
        // n is mod for private & public keys, n bit length is key length
        BigInteger n = rsa.n(p, q);

        // 3. Compute Phi(n) (Euler's totient function)
        // Phi(n) = (p-1)(q-1)
        // BigIntegers are objects and must use methods for algebraic operations
        BigInteger phi = rsa.getPhi(p, q);

        // 4. Find an int e such that 1 < e < Phi(n) 	and gcd(e,Phi) = 1
        BigInteger e = rsa.genE(phi);

        // 5. Calculate d where  d ≡ e^(-1) (mod Phi(n))
        BigInteger d = rsa.extEuclid(e, phi)[1];

//	System.out.println("p: " + p);
//	System.out.println("q: " + q);
//	System.out.println("n: " + n);
//        System.out.println("Phi: " + phi);
//        System.out.println("e: " + e);
//        
        if ((tf_chat.getText()).equals(nothing)) 
        {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } 
        else 
        {
            try 
            {
                char s = tf_chat.getText().charAt(0);
                if(s == '@')
                {
                    if(tf_chat.getText().contains(" "))
                    {
                        String names = tf_chat.getText().substring(0, tf_chat.getText().indexOf(' '));
                        String message = tf_chat.getText().substring(tf_chat.getText().indexOf(' ') + 1);
                        String encr_m = "";
                        String encr = "";
                        String holder = "";
                        int x = message.length()/8;
                        int rem = message.length()%8;
                        int z = 0;
                        int i = 0;
                        BigInteger cipherMessage = null;
                        BigInteger encrypted = null;
                        while(i < message.length())
                        {
                        	i++;
                        	holder += message.charAt(i-1);
                        	if((i%8 == 0) && (z < x) && (x != 0))
                        	{
                        		z++;
                        		cipherMessage = rsa.stringCipher(holder);
                        		encrypted = rsa.encrypt(cipherMessage, e, n);
                        		encr = encrypted.toString();
                        		if(encr.length() == 16)
                        		{
                        			encr = "0" + encr;
                        		}
                        		encr_m += encr;
                        		
                        		holder = "";
                        	}
                        	
                        	if(i == message.length() && z == x && rem != 0)
                        	{
                            		int l = 8 - rem;
                            		for(int j = 0; j < l; j++)
                            		{
                            			holder += "\0";
                            		}

                            }
                        	
                        }
                        if(rem != 0)
                        {
	                        cipherMessage = rsa.stringCipher(holder);
	                		encrypted = rsa.encrypt(cipherMessage, e, n);
	                		encr = encrypted.toString();
	                		if(encr.length() == 16)
	                		{
	                			encr = "0" + encr;
	                		}
	                		encr_m += encr;
                        }
                        // Convert string to numbers using a cipher

                        // Encrypt the ciphered message
                		
                        writer.println(username + ":" + names+" "+encr_m+";"+d+";"+n + ":" + "ChatFriend");
                        writer.flush(); // flushes the buffer
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(client_frame.this,
			                  "There is no space after name. Click Help under File menu for details",
			                  "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
                else
                {
 
                    String message = tf_chat.getText();
                    String encr_m = "";
                    String encr = "";
                    String holder = "";
                    int x = message.length()/8;
                    int rem = message.length()%8;
                    int z = 0;
                    int i = 0;
                    BigInteger cipherMessage = null;
                    BigInteger encrypted = null;
                    while(i < message.length())
                    {
                    	i++;
                    	holder += message.charAt(i-1);
                    	if((i%8 == 0) && (z < x) && (x != 0))
                    	{
                    		z++;
                    		cipherMessage = rsa.stringCipher(holder);
                    		encrypted = rsa.encrypt(cipherMessage, e, n);
                    		encr = encrypted.toString();
                    		if(encr.length() == 16)
                    		{
                    			encr = "0" + encr;
                    		}
                    		encr_m += encr;
                    	
                    		holder = "";
                    	}
                    	
                    	if(i == message.length() && z == x && rem != 0)
                    	{
                        		int l = 8 - rem;
                        		for(int j = 0; j < l; j++)
                        		{
                        			holder += "\0";
                        		}

                        }
                    	
                    }
                    if(rem != 0)
                    {
                        cipherMessage = rsa.stringCipher(holder);
                		encrypted = rsa.encrypt(cipherMessage, e, n);
                		encr = encrypted.toString();
                		if(encr.length() == 16)
                		{
                			encr = "0" + encr;
                		}
                		encr_m += encr;
                    }
                    // Convert string to numbers using a cipher

                    // Encrypt the ciphered message
   
                    writer.println(username + ":" + encr_m+";"+d+";"+n + ":" + "Chat");
                    writer.flush(); // flushes the buffer
                }
              
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        JOptionPane.showMessageDialog(client_frame.this,
        				"You must know the port number of server to connect the server\n"
                        +"Your username should be unique. Program will send an error if name already existed\n"
                        +"You can select p and q value otherwise program will generate automatically\n"
                        +"You can send message all clients or spcific one or more than one clients\n"
                        +"If you want to send message all clients then type the message and click on Send button\n"
                        +"If you want to send message specific/particular one client then follow the format which is given below:\n"
                        +"@clientname yourmessage\n"
                        +"Example: @client1 sample message which sends to client1\n"
                        +"So type @ first, then without no space type client name, then give a space and finally type your message\n"
                        +"If you want to send message specific/particular clients that's mean number of client more than one\n"
                        +"then follow the format whic is given below\n"
                        +"@clientname1@clientname2@clientname3 yourmessage\n"
                        +"Example: @client1@client2 sample message which sends to client1 and client2\n",
			                  "HELP", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(client_frame.this,
	                  "Networked Chat with RSA Encryption/Decryption, project of CS342.\n"
	                          + "1. Youlho Cha, ycha8\n"
	                          + "2. Viktor Partyka, vparty2\n"
	                          + "3. Saurav Das, sdas31\n",
	                  "About", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_aboutMenuItemActionPerformed
    public boolean isPrime(BigInteger number) {
        //check via BigInteger.isProbablePrime(certainty)
        if (!number.isProbablePrime(5))
            return false;

        //check if even
        BigInteger two = new BigInteger("2");
        if (!two.equals(number) && BigInteger.ZERO.equals(number.mod(two)))
            return false;

        //find divisor if any from 3 to 'number'
        for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(number) < 1; i = i.add(two)) { //start from 3, 5, etc. the odd number, and look for a divisor if any
            if (BigInteger.ZERO.equals(number.mod(i))) //check if 'i' is divisor of 'number'
                return false;
        }
        return true;
    }
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JTextArea clientList;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
