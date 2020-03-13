// // @author Nicholas Grokhowsky
// This program requests a file input from the user and then returns the proportion and range of words based on number of characters per word

import java.util.*;
import java.io.*;

class Authorship
{
	public static void main(String[]args) throws FileNotFoundException
	{

		// Scanner that requests input file and saves it as a File object
		// Second scanner is then created to scan the File object created
		Scanner keyboard = new Scanner (System.in);
		System.out.print("Name of input file: ");
		File f = new File (keyboard.next());
		Scanner input = new Scanner(f);

		// Vector used to store each word's length
		Vector <Number> count = new Vector<Number>();
		Vector <String> word = new Vector<String>();
		Vector<Integer> freq = new Vector<Integer>();

		// Counter variable used to store index of each word
		int counter = 0;
	
		// While loop iterates the File and inserts the word length at each vectors new index based on the counter
		// Counter is incremented each time a new word's length is added to the vector
		while(input.hasNext())
		{
			String n = input.next();
			n = n.replaceAll("[^a-zA-Z ]", "").toLowerCase();

			count.insertElementAt(n.length(), counter);
			word.insertElementAt(n, counter);
			freq.insertElementAt(0, counter);

			counter++;

		}	

		// Call method that iterates the vector passed, and returns a print out of the statistics for every instance of the int passed		
		charCount(count, 1);
		charCount(count, 2);
		charCount(count, 3);
		charCount(count, 4);
		charCount(count, 5);
		charCount(count, 6);
		charCount(count, 7);
		charCount(count, 8);
		charCount(count, 9);
		charCount(count, 10);
		charCount(count, 11);
		charCount(count, 12);
		charCount(count, 13);
		charCount(count, 14);
		charCount(count, 15);
		charCount(count, 16);
		charCount(count, 17);
		charCount(count, 18);
		charCount(count, 19);
		charCount(count, 20);


		wordUse(word, 1, freq);
		wordUse(word, 2, freq);
		wordUse(word, 3, freq);
		wordUse(word, 4, freq);
		wordUse(word, 5, freq);
		wordUse(word, 6, freq);
		wordUse(word, 7, freq);
		wordUse(word, 8, freq);
		wordUse(word, 9, freq);
		wordUse(word, 10, freq);

		System.out.println();
		System.out.println("Mean frequency: " + meanFreq(count));
		System.out.println("Standard deviation: " + sd(count));
		System.out.println();

		System.out.println("KEY WORDS: ");

		System.out.println(word.size());
	

		//printKeyWords(word);


	}

	// Method that iterates a vector and returns the necessary statistics in a printed statement
	public static void charCount(Vector count, int num)
	{
		// Counter variable determines numer of instances of num
		int counter = 0;
		
		// Iterate over vector to increment counter each time num is identified
		for(int i=0; i<count.size(); i++)
		{
			if(count.elementAt(i).equals(num))
			{
				counter++;
			}

		}

		// Variable to store proportion of each num
		double proportion = ((double)counter/count.size())*100;

		// Print out of statistics in a statement
		System.out.printf("Proportion of %d-letter words: %.2f%% (%d words)%n", num, proportion, counter);
	}

	// Method that iterates a vector and returns the necessary statistics in a printed statement
	public static void wordUse(Vector word, int num, Vector freq)
	{
		// Empty list variables to store words and their frequencies
		Vector<String> temp = new Vector<String>();
		Vector<Integer> f = new Vector<Integer>();

		// Beginning of output to client
		System.out.println();
		System.out.println("####################################################");
		System.out.println("###    " + num + " letter words:                          ###");
		System.out.println("####################################################");

		// Counter variable determines numer of instances of num
		int counter = 0;
		
		// Iterate over vector to increment counter each time num is identified
		for(int i=0; i<word.size(); i++)
		{
			String w = (String)word.elementAt(i);

			if(w.length() == num)
			{
				String n = (String)word.elementAt(i);
				temp.insertElementAt(n, counter);
				counter++;
			}
		}

		// Use of internal method 'frequency' to calculate the frequencies of words
		frequency(temp, f);

		// Remove duplicates of words and frequencies using internal methods
		temp = removeDuplicates(temp);
		f = removeDuplicatesFreq(temp, f);

		// Iterate length of frequency list and add element to instance variable 'freq'
		for(int i=0; i<f.size(); i++)
		{
			freq.add(f.elementAt(i));
		}

		// Finalize output
		System.out.println("WORDS:	                               FREQUENCIES:");
		System.out.println();

		// Iterate 'temp' list of words and print each word plus frequncy of words
		for(int i=0; i<temp.size(); i++)
		{	
			System.out.println("    	" + temp.elementAt(i) + "    " + " FREQUENCY:	" + f.elementAt(i));
		}

	}

	// method removes duplicate words from a vector of words
	public static Vector<String> removeDuplicates(Vector word)
	{
		// Create an empty vector of strings to store the new vector with duplicates removed
		Vector<String> output = new Vector<String>();

		// Iterate the parameter passed 'word' at index 0
		for(int i=0; i<word.size(); i++)
		{
			// Iterate the parameter 'word' again starting at index 1
			for(int j=i+1; j<word.size(); j++)
			{
				// Compare values that are not null and set duplicates to empty string
				if((!word.elementAt(i).equals(null)) && (word.elementAt(i).equals(word.elementAt(j))))
				{
					word.set(j, "");
				}
			}
		}

		// Counter set to 0 
		int counter = 0;

		// Iterate 'word' vector and if the word does not equal an empty string add it to the output vector
		while((counter < word.size()))
		{
			if(!word.elementAt(counter).equals(""))
			{
				output.addElement((String)word.elementAt(counter));
			}

			// Increment counter
			counter++;
		}

		return output;
	}

	// Method that returns the frequency of a word used and stores it in the instance variable passed as parameter
	public static void frequency(Vector word, Vector freq)
	{
		// Iterate 'word' vector and increment counter if words match
		for(int i=0; i<word.size(); i++)
		{
			// First count of word equals 1
			int counter = 1;

			// Second iteration starts at index 1 to compare to previous iteration
			for(int j=i+1; j<word.size(); j++)
			{
				if((!word.elementAt(i).equals(null)) && (word.elementAt(i).equals(word.elementAt(j))))
				{
					counter++;
				}
			}

			// Insert the counter variable at the same index as the word identified in 'word'
			freq.insertElementAt(counter, i);
		}
	}

	// 
	public static Vector<Integer> frequencyVector(Vector word)
	{

		Vector<Integer> f = new Vector<Integer>();
		
		for(int i=0; i<word.size(); i++)
		{
			int counter = 1;

			for(int j=i+1; j<word.size(); j++)
			{
				if((!word.elementAt(i).equals(null)) && (word.elementAt(i).equals(word.elementAt(j))))
				{
					counter++;
				}
			}

			f.insertElementAt(counter, i);
		}

		return f;
	}

	// Method to remove duplicate frequencies in same order as removeDuplicates
	public static Vector<Integer> removeDuplicatesFreq(Vector word, Vector freq)
	{
		// Create an output vector
		Vector<Integer> output = new Vector<Integer>();

		// Incement 'word' parameter at index 0
		for(int i=0; i<word.size(); i++)
		{	
			// Increment 'word' parameter a second time at index 1
			for(int j=i+1; j<word.size(); j++)
			{
				// Test if words are equal and if so set freq to unique value at index i
				if((!word.elementAt(i).equals(null)) && (word.elementAt(i).equals(word.elementAt(j))))
				{
					freq.set(j, 10000000);
				}
			}
		}

		// Set counter to 0
		int counter = 0;

		// Iterate 'freq' vector and if equals unique value at frequency to output
		while((counter < freq.size()))
		{
			if(!freq.elementAt(counter).equals(10000000))
			{
				output.addElement((Integer)freq.elementAt(counter));
			}

			// Increment counter
			counter++;
		}

		return output;
	}

	// Method calculates the mean value of the word frequencies
	public static double meanFreq(Vector freq)
	{
		int f = 0;

		// Iterate frequencies and add them
		for(int i=0; i<freq.size(); i++)
		{
			f += (int)freq.elementAt(i);
		}

		// Calculate the mean
		double mean = (f/1.0)/(freq.size()/1.0);
		

		return mean;
	}

	// Method calculates the standard deviation of the frequencies
	public static double sd(Vector freq)
	{
		double sd = 0.0;
		double mean = meanFreq(freq);

		// Increment frequencies and calculate SD
		for(int i=0; i<freq.size(); i++)
		{
			sd += ((int)freq.elementAt(i)/1.0 - mean) * ((int)freq.elementAt(i)/1.0 - mean)/freq.size();
		}

		// Final square root to finalize standard deviation
		sd =  java.lang.Math.sqrt(sd);

		return sd;

	}

	// Method to output  the final key words
	public static void printKeyWords(Vector word)
	{
		// Create a copy of the 'word' parameter
		Vector<Integer> f = frequencyVector(word);
		/*
		Vector<String> s = removeDuplicates(word);
		*/

		// Create
		int d = (int) (meanFreq(f) + sd(f));

		// Increment the length of 'word'
		for(int i=0; i<f.size(); i++)
		{
			// Create an object at element i 
			Object x = f.elementAt(i);
			// Create an object Integer of the previous object
			Integer ddd = (Integer)x;
			// Create an Integer object of the value of d
			Integer dd = Integer.valueOf(d);

			// If the value at element i is larger than the mean + 1 magnitude of standard deviation print
			if(ddd >= dd)
			{		
				System.out.println(word.elementAt(i) + "                " + f.elementAt(i));
			}


		}

	}

}