import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class mines implements  ActionListener, WindowListener, MouseListener
{
	JFrame base;
	JPanel p[]=new JPanel[64],p1,p2;
	JLabel mn,sc;
	JButton b[]=new JButton[64],b1,b2;
	MLabel l[]=new MLabel[64];
	ImageIcon img;
	int invals[]=new int[19],onetym=0,stack[]=new int[64],top=0,count=64;
	
	mines()
	{
        onetym=0;
		top=0;
		count=64;
		for(int i=0;i<19;i++)
		{
			invals[i]=-1;
		}
		base=new JFrame("Mines");
        base.setLayout(null);
		
		mn=new JLabel("                 Get ready to Brainstorm, cos you have to cheat DEATH!!!  ");
		mn.setBorder(BorderFactory.createLineBorder(Color.black));
		sc=new JLabel("            Good Luck!");
		sc.setBorder(BorderFactory.createLineBorder(Color.black));
		
		b1=new JButton("New Game");
		b1.addActionListener(this);
		b2=new JButton("Exit");
		b2.addActionListener(this);
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(0,3,3,3));
		
		p2=new JPanel();
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		p2.setLayout(new GridLayout(8,8,2,2));    
        
		p1.add(b1);p1.add(sc);p1.add(b2);
		base.addWindowListener(this);
		for(int i=0;i<64;i++)
        {
            p[i]=new JPanel();
			p[i].setBorder(BorderFactory.createLineBorder(Color.black));
			p[i].setLayout(new BorderLayout());
			b[i]=new JButton();
			
			b[i].addMouseListener(this);
			img=new ImageIcon(this.getClass().getResource("0.png"));
			l[i]=new MLabel(img);
			l[i].setc(0);
			p[i].add(b[i]);
			p2.add(p[i]);
        }
		mn.setBounds(9,3,422,20);
		p1.setBounds(9,28,422,40);
		p2.setBounds(9,73,422,502);
		base.add(mn);
		base.add(p1);
		base.add(p2);
		base.setSize(450,615);
		base.setResizable(false);
        base.setVisible(true);
		
		
	}
	public static void main(String ar[])
	{
		mines m=new mines();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton nb=(JButton)e.getSource();
		if(nb==b1)
		{
			onetym=0;
			top=0;
			count=64;
			for(int i=0;i<19;i++)
			{
				invals[i]=-1;
			}
			sc.setText("            Good Luck!");
			for(int i=0;i<64;i++)
			{
				b[i]=new JButton();
				b[i].addMouseListener(this);
				img=new ImageIcon(this.getClass().getResource("0.png"));
				l[i]=new MLabel(img);
				l[i].setc(0);
				p[i].removeAll();
				p[i].add(b[i]);
				p[i].revalidate();
				p2.add(p[i]);
			}
			p2.revalidate();
			base.repaint();
		}
		else if(nb==b2)
		{
			System.exit(0);
		}
	}
	
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	public void windowClosed(WindowEvent e)
	{}
	public void windowOpened(WindowEvent e)
	{}
	public void windowIconified(WindowEvent e)
	{}
	public void windowDeiconified(WindowEvent e)
	{}
	public void windowActivated(WindowEvent e)
	{}
	public void windowDeactivated(WindowEvent e)
	{}
	public void mouseReleased(MouseEvent e)
	{}
	public void mousePressed(MouseEvent e)
	{}
	public void mouseExited(MouseEvent e)
	{}
	public void mouseEntered(MouseEvent e)
	{}
	public void mouseClicked(MouseEvent e)
	{
		JButton mb=(JButton)e.getSource();
		int i;
		for(i=0;i<64;i++)
        {
			if(mb==b[i])
			{
				break;
			}
		}
		if(e.getButton()==MouseEvent.BUTTON3)
		{
			if(l[i].getdiscv()<9&&onetym==1)
			{
				img=new ImageIcon(this.getClass().getResource("flag.png"));
				b[i]=new JButton(img);
				b[i].addMouseListener(this);
				p[i].removeAll();
				p[i].add(b[i]);
				p[i].revalidate();
				l[i].setdiscv(9);
			}
			else if(l[i].getdiscv()==9)
			{
				b[i]=new JButton();
				b[i].addMouseListener(this);
				p[i].removeAll();
				p[i].add(b[i]);
				p[i].revalidate();
				l[i].setdiscv(l[i].getc());
			}
		}
		else if(e.getButton()==MouseEvent.BUTTON1)
		{
			
		if(onetym==0)
		{
			mineinit(i);
        }
		
		
		if(l[i].getdiscv()==9)
		{
			
		}
		else if(l[i].getdiscv()==0)
		{
			l[i].setc(9);
			p[i].removeAll();
			p[i].add(l[i]);
			p[i].revalidate();
			count=count-1;
			
			if(count==10)
			{
				gameover(100);
			}
			
			stack[0]=i;
			top=0;
			while(top>=0)
			{
				top=stackdiscv(top);
			}
			
		}
		else if(l[i].getdiscv()>0)
		{
			l[i].setc(9);
			p[i].removeAll();
			p[i].add(l[i]);
			p[i].revalidate();
			count=count-1;
			if(count==10)
			{
				gameover(100);
			}
		}
		else
		{
				gameover(i);
		}	
		p2.revalidate();
		base.repaint();
		}
		
	}
	public void setarea(int j)
	{
		int c;
						try
						{
							if(l[j-9]!=null&&((j%8)>0))
							{
								c=l[j-9].getc();
								if(c>=0)
								{
									c=c+1;
									img=new ImageIcon(this.getClass().getResource(c+".png"));
									l[j-9]=new MLabel(img);
									l[j-9].setc(c);
									
								}
							}
						}
						catch(Exception e3)
						{}
						try
						{
							if(l[j-8]!=null)
							{
								c=l[j-8].getc();
								if(c>=0)
								{
									c=c+1;
									img=new ImageIcon(this.getClass().getResource(c+".png"));
									l[j-8]=new MLabel(img);
									l[j-8].setc(c);
									
								}
							}
						}
						catch(Exception e4)
						{}
						try
						{
							if(l[j-7]!=null&&((j%8)<7))
							{
								c=l[j-7].getc();
								if(c>=0)
								{
									c=c+1;
									img=new ImageIcon(this.getClass().getResource(c+".png"));
									l[j-7]=new MLabel(img);
									l[j-7].setc(c);
									
								}
							}
						}
						catch(Exception e5)
						{}
						try
						{
							if(l[j-1]!=null&&((j%8)>0))
							{
								c=l[j-1].getc();
								if(c>=0)
								{
									c=c+1;
									img=new ImageIcon(this.getClass().getResource(c+".png"));
									l[j-1]=new MLabel(img);
									l[j-1].setc(c);
									
								}
							}
						}
						catch(Exception e6)
						{}
						try
						{
							if(l[j+1]!=null&&((j%8)<7))
							{
								c=l[j+1].getc();
								if(c>=0)
								{
									c=c+1;
									img=new ImageIcon(this.getClass().getResource(c+".png"));
									l[j+1]=new MLabel(img);
									l[j+1].setc(c);
									
								}
							}
						}
						catch(Exception e7)
						{}
						try
						{
							if(l[j+7]!=null&&((j%8)>0))
							{
								c=l[j+7].getc();
								if(c>=0)
								{
									c=c+1;
									img=new ImageIcon(this.getClass().getResource(c+".png"));
									l[j+7]=new MLabel(img);
									l[j+7].setc(c);
									
								}
							}
						}
						catch(Exception e8)
						{}
						try
						{
							if(l[j+8]!=null)
							{
								c=l[j+8].getc();
								if(c>=0)
								{
									c=c+1;
									img=new ImageIcon(this.getClass().getResource(c+".png"));
									l[j+8]=new MLabel(img);
									l[j+8].setc(c);
									
								}
							}
						}
						catch(Exception e9)
						{}
						try
						{
							if(l[j+9]!=null&&((j%8)<7))
							{
								c=l[j+9].getc();
								if(c>=0)
								{
									c=c+1;
									img=new ImageIcon(this.getClass().getResource(c+".png"));
									l[j+9]=new MLabel(img);
									l[j+9].setc(c);
									
								}
							}
						}
						catch(Exception e10)
						{}
						
	}
	public void mineinit(int i)
	{
		
				invals[0]=i; invals[1]=i-1; invals[2]=i+1; invals[3]=i-8;
				invals[4]=i+8; invals[5]=i-9; invals[6]=i+9; invals[7]=i-7; invals[8]=i+7;
				int x,fl=0,k;
				while(fl<10)
				{
					x=(int)(Math.random()*64);
					for(k=0;k<19;k++)
					{
						if(x==invals[k])
							break;
					}
					if(k==19)
					{
						fl++;
						invals[fl+8]=x;
						try
						{
							
							img=new ImageIcon(this.getClass().getResource("mine.png"));
							l[x]=new MLabel(img);
							l[x].setc(-1);

							setarea(x);
						}
						catch(Exception e1)
						{
							System.out.println(e1.getMessage());
						}
					}
				}
				
			
		onetym=1;
	}
	
	public int stackdiscv(int top)
	{
		int j=stack[top];
		top=top-1;
		
		int c;
						try
						{
							if(l[j-9]!=null&&((j%8)>0))
							{
								c=l[j-9].getdiscv();
								if(c==0)
								{
									top=top+1;
									stack[top]=j-9;
									
									
								}
								if(c<9)
								{
									l[j-9].setc(9);
									p[j-9].removeAll();
									p[j-9].add(l[j-9]);
									p[j-9].revalidate();
									count=count-1;
									if(count==10)
									{
										gameover(100);
									}
								}	
							}
						}
						catch(Exception e3)
						{}
						try
						{
							if(l[j-8]!=null)
							{
								c=l[j-8].getdiscv();
								if(c==0)
								{
									top=top+1;
									stack[top]=j-8;
									
								}
								if(c<9)
								{
									l[j-8].setc(9);
									p[j-8].removeAll();
									p[j-8].add(l[j-8]);
									p[j-8].revalidate();
								count=count-1;
									if(count==10)
									{
										gameover(100);
									}
								}
							}
						}
						catch(Exception e4)
						{}
						try
						{
							if(l[j-7]!=null&&((j%8)<7))
							{
								c=l[j-7].getdiscv();
								if(c==0)
								{
									top=top+1;
									stack[top]=j-7;
									
								}
								if(c<9)
								{
									l[j-7].setc(9);
									p[j-7].removeAll();
									p[j-7].add(l[j-7]);
									p[j-7].revalidate();
									count=count-1;
									if(count==10)
									{
										gameover(100);
									}
								}
							}
						}
						catch(Exception e5)
						{}
						try
						{
							if(l[j-1]!=null&&((j%8)>0))
							{
								c=l[j-1].getdiscv();
								if(c==0)
								{
									top=top+1;
									stack[top]=j-1;
									
								}
								if(c<9)
								{
									l[j-1].setc(9);
									p[j-1].removeAll();
									p[j-1].add(l[j-1]);
									p[j-1].revalidate();
									count=count-1;
									if(count==10)
									{
										gameover(100);
									}
								}
							}
						}
						catch(Exception e6)
						{}
						try
						{
							if(l[j+1]!=null&&((j%8)<7))
							{
								c=l[j+1].getdiscv();
								if(c==0)
								{
									top=top+1;
									stack[top]=j+1;
									
								}
								if(c<9)
								{
									l[j+1].setc(9);
									p[j+1].removeAll();
									p[j+1].add(l[j+1]);
									p[j+1].revalidate();
									count=count-1;
									if(count==10)
									{
										gameover(100);
									}
								}
							}
						}
						catch(Exception e7)
						{}
						try
						{
							if(l[j+7]!=null&&((j%8)>0))
							{
								c=l[j+7].getdiscv();
								if(c==0)
								{
									top=top+1;
									stack[top]=j+7;
									
								}
								if(c<9)
								{
									l[j+7].setc(9);
									p[j+7].removeAll();
									p[j+7].add(l[j+7]);
									p[j+7].revalidate();
									count=count-1;
									if(count==10)
									{
										gameover(100);
									}
								}
							}
						}
						catch(Exception e8)
						{}
						try
						{
							if(l[j+8]!=null)
							{
								c=l[j+8].getdiscv();
								if(c==0)
								{
									top=top+1;
									stack[top]=j+8;
									
								}
								if(c<9)
								{
									l[j+8].setc(9);
									p[j+8].removeAll();
									p[j+8].add(l[j+8]);
									p[j+8].revalidate();
									count=count-1;
									if(count==10)
									{
										gameover(100);
									}
								}
							}
						}
						catch(Exception e9)
						{}
						try
						{
							if(l[j+9]!=null&&((j%8)<7))
							{
								c=l[j+9].getdiscv();
								if(c==0)
								{
									top=top+1;
									stack[top]=j+9;
									
								}
								if(c<9)
								{
									l[j+9].setc(9);
									p[j+9].removeAll();
									p[j+9].add(l[j+9]);
									p[j+9].revalidate();
									count=count-1;
									if(count==10)
									{
										gameover(100);
									}
								}
								
							}
						}
						catch(Exception e10)
						{}
		return top;
	}
	
	public void gameover(int i)
	{
		if(i==100)
		{
			sc.setText("       Y o u   W o n!!!   :)");
			p1.revalidate();
		}
		else
		{
			img=new ImageIcon(this.getClass().getResource("mine2.png"));
			l[i]=new MLabel(img);
			l[i].setc(-1);
			sc.setText("     Y o u   L o s e!!!   :(");
			p1.revalidate();
		}
		for(i=0;i<64;i++)			
		{
			
			if(l[i].getc()==-1)
			{
				p[i].removeAll();
				p[i].add(l[i]);
				p[i].revalidate();
			}
			l[i].setc(9);
		}
		
	}
}