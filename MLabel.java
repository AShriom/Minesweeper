import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


class MLabel extends JLabel
{
	int c=0,discv=0;
	public int getc()
	{
		return c;
	}
	public int getdiscv()
	{
		return discv;
	}
	public void setdiscv(int discv)
	{
		this.discv=discv;
	}
	public void setc(int c)
	{
		this.c=c;
		discv=c;
	}
	MLabel(ImageIcon img)
	{
		super(img);
	}
}