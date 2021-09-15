package asroche.hw3;
import algs.hw3.ShakespearePlay;
import edu.princeton.cs.algs4.StdRandom;
public class Q3 {

	public static void main(String[] args)
	{
		BST mostFrequentAll = new BST();
		String title = "";
		String w1 = "";
		String w2 = "";
		String w3 = "";
		String w4 = "";
		String w5 = "";
		for(int i=1; i<=38; i++)
		{
			ShakespearePlay sp = new ShakespearePlay(i);

			for(String s:sp)
			{	

				if(mostFrequentAll.get(s) == null)
					mostFrequentAll.put(s, 1);

				else
					mostFrequentAll.put(s, mostFrequentAll.get(s)+1);
			}


		}


		System.out.println("The most frequent word in all of Shakespear's 38 plays is: " + mostFrequentAll.mostFrequent());





		for (int n = 1; n <= 38; n ++) {
			BST[] arr = new BST[n];
			ShakespearePlay sp1 = new ShakespearePlay(n);

			for (int i = 0; i < n; i++) { 

				arr[i] = new BST();

			}

			for(String s:sp1)
			{	
				if(arr[n-1].get(s) == null)
					arr[n-1].put(s, 1);
				else
					arr[n-1].put(s, arr[n-1].get(s)+1);
			}


			System.out.println("");
			//System.out.println("PLAY " + n + " :" + sp1.getTitle());
			String word1 = arr[n-1].mostFrequent();
			//System.out.println("The most frequent word in play " + n + " is: " + word1);
			arr[n-1].delete(arr[n-1].mostFrequent());
			String word2 = arr[n-1].mostFrequent();
			//System.out.println("The second most frequent word in play " + n + " is: " + word2);
			arr[n-1].delete(arr[n-1].mostFrequent());
			String word3 = arr[n-1].mostFrequent();
			//System.out.println("The third most frequent word in play " + n + " is: " + word3);
			arr[n-1].delete(arr[n-1].mostFrequent());
			String word4 = arr[n-1].mostFrequent();
			//System.out.println("The fourth most frequent word in play " + n + " is: " + word4);
			arr[n-1].delete(arr[n-1].mostFrequent());
			String word5 = arr[n-1].mostFrequent();
			//System.out.println("The fifth most frequent word in play " + n + " is: " + word5);

			System.out.println(word1 + " " + word2 + " " +word3 + " " + word4 + " " + word5 + ": " + sp1.getTitle() + " Play No. " + n);
			if(!(word1.equals(mostFrequentAll.mostFrequent())) && !(word2.equals(mostFrequentAll.mostFrequent())) && !(word3.equals(mostFrequentAll.mostFrequent())) && !(word4.equals(mostFrequentAll.mostFrequent())) && !(word5.equals(mostFrequentAll.mostFrequent())))
			{
				title = sp1.getTitle();
				w1 = word1;
				w2 = word2; 
				w3 = word3;
				w4 = word4;
				w5 = word5;
				
			}
			
			
		}

		System.out.println("--------------------------------------------------------------------");
		System.out.println(title + " is the only play that does not contain " + mostFrequentAll.mostFrequent().toUpperCase() + " in its top 5");
		System.out.println("The 5 most common words in " + title + " is: " + w1.toUpperCase() + " " + w2.toUpperCase() + " " + w3.toUpperCase() + " " + w4.toUpperCase() + " " + w5.toUpperCase());

	}


}
