//Main code copied from here - https://www.geeksforgeeks.org/%C2%AD%C2%ADkasais-algorithm-for-construction-of-lcp-array-from-suffix-array/

// C++ program for building LCP array for given text 
#include <bits/stdc++.h> 
using namespace std; 
  
// Structure to store information of a suffix 
struct suffix 
{ 
    int index;  // To store original index 
    int rank[2]; // To store ranks and next rank pair 
}; 
  
// A comparison function used by sort() to compare two suffixes 
// Compares two pairs, returns 1 if first pair is smaller 
int cmp(struct suffix a, struct suffix b) 
{ 
    return (a.rank[0] == b.rank[0])? (a.rank[1] < b.rank[1] ?1: 0): 
           (a.rank[0] < b.rank[0] ?1: 0); 
} 
  
// This is the main function that takes a string 'txt' of size n as an 
// argument, builds and return the suffix array for the given string 
vector<int> buildSuffixArray(string txt, int n) 
{ 
    // A structure to store suffixes and their indexes 
    struct suffix suffixes[n]; 
  
    // Store suffixes and their indexes in an array of structures. 
    // The structure is needed to sort the suffixes alphabatically 
    // and maintain their old indexes while sorting 
    for (int i = 0; i < n; i++) 
    { 
        suffixes[i].index = i; 
        suffixes[i].rank[0] = txt[i] - '1'; 
        suffixes[i].rank[1] = ((i+1) < n)? (txt[i + 1] - '1'): -1; 
    } 
  
    // Sort the suffixes using the comparison function 
    // defined above. 
    sort(suffixes, suffixes+n, cmp); 
    for (int i = 0; i < n; i++) {

	    cout << suffixes[i].index << " " << suffixes[i].rank[0] << " " << suffixes[i].rank[1] << "\n";
	   }

    cout << "\n" ;
  
    // At his point, all suffixes are sorted according to first 
    // 2 characters.  Let us sort suffixes according to first 4 
    // characters, then first 8 and so on 
    int ind[n];  // This array is needed to get the index in suffixes[] 
    // from original index.  This mapping is needed to get 
    // next suffix. 
    for (int k = 4; k < 2*n; k = k*2) 
    { 
        // Assigning rank and index values to first suffix 
        int rank = 0; 
        int prev_rank = suffixes[0].rank[0]; 
        suffixes[0].rank[0] = rank; 
        ind[suffixes[0].index] = 0; 
  
        // Assigning rank to suffixes 
        for (int i = 1; i < n; i++) 
        { 
            // If first rank and next ranks are same as that of previous 
            // suffix in array, assign the same new rank to this suffix 
            if (suffixes[i].rank[0] == prev_rank && 
                    suffixes[i].rank[1] == suffixes[i-1].rank[1]) 
            { 
                prev_rank = suffixes[i].rank[0]; 
                suffixes[i].rank[0] = rank; 
            } 
            else // Otherwise increment rank and assign 
            { 
                prev_rank = suffixes[i].rank[0]; 
                suffixes[i].rank[0] = ++rank; 
            } 
            ind[suffixes[i].index] = i; 
        } 
  
        // Assign next rank to every suffix 
        for (int i = 0; i < n; i++) 
        { 
            int nextindex = suffixes[i].index + k/2; 
            suffixes[i].rank[1] = (nextindex < n)? 
                                  suffixes[ind[nextindex]].rank[0]: -1; 
        } 
  
        // Sort the suffixes according to first k characters 
        sort(suffixes, suffixes+n, cmp); 
    } 
  
    // Store indexes of all sorted suffixes in the suffix array 
    vector<int>suffixArr; 
    for (int i = 0; i < n; i++) 
        suffixArr.push_back(suffixes[i].index); 
  
    for (int i = 0; i < n; i++) {

	    cout << suffixes[i].index << " " << suffixes[i].rank[0] << " " << suffixes[i].rank[1] << "\n";
	   }

    cout << "\n" ;
    // Return the suffix array 
    return  suffixArr; 
} 
  
/* To construct and return LCP */
vector<int> kasai(string txt, vector<int> suffixArr) 
{ 
    int n = suffixArr.size(); 
  
    // To store LCP array 
    vector<int> lcp(n, 0); 
  
    // An auxiliary array to store inverse of suffix array 
    // elements. For example if suffixArr[0] is 5, the 
    // invSuff[5] would store 0.  This is used to get next 
    // suffix string from suffix array. 
    vector<int> invSuff(n, 0); 
  
    // Fill values in invSuff[] 
    for (int i=0; i < n; i++) 
        invSuff[suffixArr[i]] = i; 
  
    // Initialize length of previous LCP 
    int k = 0; 
  
    // Process all suffixes one by one starting from 
    // first suffix in txt[] 
    for (int i=0; i<n; i++) 
    { 
        /* If the current suffix is at n-1, then we don’t 
           have next substring to consider. So lcp is not 
           defined for this substring, we put zero. */
        if (invSuff[i] == n-1) 
        { 
            k = 0; 
            continue; 
        } 
  
        /* j contains index of the next substring to 
           be considered  to compare with the present 
           substring, i.e., next string in suffix array */
        int j = suffixArr[invSuff[i]+1]; 
  
        // Directly start matching from k'th index as 
        // at-least k-1 characters will match 
        while (i+k<n && j+k<n && txt[i+k]==txt[j+k]) 
            k++; 
  
        lcp[invSuff[i]] = k; // lcp for the present suffix. 
  
        // Deleting the starting character from the string. 
        if (k>0) 
            k--; 
    } 
  
    // return the constructed lcp array 
    return lcp; 
} 
  
// Utility function to print an array 
void printSAandLCPArr(string s, vector<int>arr,vector<int>lcp, int n) 
{ 
    for (int i = 0; i < n; i++) 
        cout << i << " " << arr[i] << " " << lcp[i] << " " << s.substr(arr[i]) << " \n"; 
    cout << endl; 
} 

// Utility function to print an array 
void printArrBare(vector<int>arr) 
{ 
    for (int i = 0; i < arr.size(); i++) 
        cout << arr[i] << " "; 
    cout << endl; 
} 
  
  
// Driver program 
int main() 
{ 
    string str; 
    cin >> str;
  
    vector<int>suffixArr = buildSuffixArray(str, str.length()); 
    int n = suffixArr.size(); 
  
    vector<int>lcp = kasai(str, suffixArr); 
    //cout << "Suffix Array and LCP : \n"; 
    printSAandLCPArr(str,suffixArr,lcp, n); 

    unsigned long int max = str.size();
    //unsigned long int max = 0;
    vector<int>stackPos(n,0);
    vector<int>stackVal(n,0);

    int p = 0;
	

    for (int i = 0; i < n; i++){
        printArrBare( stackPos);
        printArrBare( stackVal);
	cout << "\n";
	if(stackVal[stackPos[p]] < lcp[i]){
		//cout << "\n 1 - 1\n";
		stackVal[p] = lcp[i];
		stackPos[p] = i-1;
	        p++;
	} else if (lcp[i] != 0 && stackVal[p] == lcp[i]){
		//cout << "\n 1 - 2\n";
		//do nothing
	} else {
		//cout << "\n 1 - 3\n";
		unsigned long int x;
		for(;;){
		        cout << "\n 1 - 3 - 1\n";
			//we are doing extra 0 * 0 multiplication, but won't fix for now
			x = stackVal[p] * (i - stackPos[p]);
			//BUG! we don't want to add 1 in some cases?
			cout << " " << stackVal[p] << " " << (1+i - stackPos[p]) << " \n";
			if(x > max){
		 	cout << "\n "<< x << " " << max << " "  << stackVal[p] << " i=" << i << " sP[p]=" << stackPos[p] << " \n";
				max = x;
			}
			if(p > 0){
				//cout << "\n 1 - 3 - 2\n";
				if(stackVal[p - 1] >= lcp[i]){
					//cout << "\n 1 - 3 - 3\n";
					stackVal[p] = 0;
					stackPos[p] = 0;
					p--;
				} else {
					cout << "\n 1 - 3 - 4\n";
					stackVal[p] = lcp[i];
					//stackPos[p] = i;
					break;	
				}
			} else {
				//cout << "\n 1 - 3 - 5\n";
				break;
			}
		}

	}
	
    }    
    //printArrBare( stackPos);
    //printArrBare( stackVal);
    cout << max << "\n";

    return 0; 
} 

