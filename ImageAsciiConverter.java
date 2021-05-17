import java.io.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
  *  Program to Display an image in ASCII Art Format as Output,
  *
  *    1.) Compile a java file "AsciiImageConverter.java"
  *
  *    2.) Run the class File in CLI(Command Prompt / Terminal) with image name
  *            Example = java AsciiImageConverter java.png
  *                       * (java.png is the image name of ur expecting image)
  *                       *  Load any image you're interested in (Make sure u are typing correct image name)
  *
  *    -> If you expecting to Run this code in your preferred IDE, skip all other instruction and pass the image name directly in " line no 65 "
  *                       * (i,e) remove fileName[0] and pass your arugument as your image FileName ('java.png") instead of fileName[0]
  *            Example @Line 65 =====> imageConv.convertImageToAscii("java.png");
  *
  *    -> This program also supports single launch source file https://openjdk.java.net/jeps/330, Run the program directly with 
  *                1.)  java AsciiImageConverter.java java.png
  *                2.)  You are expected to have java-11 to perform single launch source file execution in terminal
  *
  *    ** NOTE :  
  *                1.) Algorithm Tested with jpg and png Image formats only
  *                2.) Always run below 150 * 150 dimension height and width images for better viewing
  *                         (provided some images for testing the code , Check SampleImages/ Folder)
  *                3.) Keep executing images in source file directory --> (i,e) Outside of SampleImage folder
  *
  *    \> Good Luck ☺, Do Leave a star on github if u like the project..
  *     
  *  @Github -> https://github.com/dhvakr/Image-To-Ascii_Art
  *  @Twitter -> https://twitter.com/dhvakr  
  *           
  *  @version May-15
  *  @since 2021
  *  @author DIVAKARAN
*/ 

public final class ImageAsciiConverter 
{
    private BufferedImage img;
    private double pixelValue;
    private PrintWriter prntwrt;
    private FileWriter filewrt;

    public ImageAsciiConverter() throws IOException 
    {
        prntwrt = new PrintWriter(filewrt = new FileWriter("asciiart.txt", true));
    }
    
    /**
     * 
     * @param fileName
     *        Takes image name while excuting via terminal
     * @exception IOException 
     *            if file is not found or readable
     */
    public static void main(final String... fileName)
    {
        try 
        {
          ImageAsciiConverter imageConv = new ImageAsciiConverter();
          imageConv.convertImageToAscii(fileName[0]);

          FileReader fr = new FileReader("asciiart.txt");
          int i;
          while ((i = fr.read()) != -1) 
          {
              System.out.print((char) i);
          }
          PrintWriter writer = new PrintWriter("asciiart.txt");
          writer.print("");
          writer.close();
          fr.close();
        }
        catch(final ArrayIndexOutOfBoundsException nullValue)
        {
          System.out.println("\nPlease enter imagename after -> [ java ImageAsciiConverter [ImageName] ]");
          System.out.println("\t--------- OR ---------");
          System.out.println("Try Run -> \' java ImageAsciiConverter java.png \'");
          System.out.println();
        }
        catch(final IOException notFound)
        {
            System.err.print("Error !! , check the IMAGE NAME you Entered  ");
            System.out.print("[ OR ]  Make Sure you kept images in source folder");
        }
    }
    /**
     * Method to Take an image and Convert them into Ascii format
     * 
     * @param imageName     Takes Image to convert into asciiart
     * @throws IOException  
     */
    public void convertImageToAscii(final String imageName) throws IOException 
    {
        img = ImageIO.read(new File(imageName));   

        for (int i = 0; i < img.getHeight(); i++) 
        {
            for (int j = 0; j < img.getWidth(); j++) 
            {
                Color pixelcolor = new Color(img.getRGB(j, i));
                pixelValue = (((pixelcolor.getRed() * 0.30) + (pixelcolor.getBlue() * 0.59) + (pixelcolor.getGreen() * 0.11)));
                print(colorToChar(pixelValue));  // pass pixel value to print
            }
            prntwrt.println("");
            prntwrt.flush();
            filewrt.flush(); 
        }
    }
    /**
     * This Method return the ASCII art to print method
     * 
     * @param pixval
     * @return a character to print 
     */
    public char colorToChar(final double pixval) 
    {
        if (pixval >= 240) 
          return ' ';
        else if (pixval >= 210) 
          return '.';
        else if (pixval >= 190) 
          return '*';
        else if (pixval >= 170) 
          return '+';
        else if (pixval >= 120) 
          return '^';
        else if (pixval >= 110) 
          return '$';
        else if (pixval >= 80) 
          return '4';
        else if (pixval >= 60) 
          return '#';
        else 
          return ' '; 
    }
    /**
     * Prints the ascii characters
     * 
     * @param asciiArt
     * @throws IOException
     */
    public void print(char asciiArt) throws IOException 
    {
        prntwrt.print(asciiArt);
        prntwrt.flush();
        filewrt.flush();    
    }   
}
    