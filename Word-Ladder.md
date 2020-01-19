https://leetcode.com/problems/word-ladder/

```Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.```

Soluiton:
```
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if(beginWord==null||endWord==null||endWord.length()!=beginWord.length()){
            return 0;
        }

        int len=endWord.length();

        Set<String> set=new HashSet<>();

        for(String word:wordList){
            if(word.length()==len)
                set.add(word);
        }

        set.remove(beginWord);

        class Details{
            String beginWord;
            int index;
            //    Set<String> set;
            int count;
            public Details(String beginWord,
                int index,
               int count){
                this.beginWord=beginWord;
                this.index=index;
                //      this.set=new HashSet<>(set);
                this.count=count;
            }
        }

        Queue<Details> queue=new LinkedList<>();
        queue.add(new Details(beginWord,0,0));
        Map<String,Boolean> map=new HashMap<>();
        while (!queue.isEmpty()){
            Details details=queue.remove();

            if(details.beginWord.equals(endWord)){
                return details.count+1;
            }

            if(details.index==beginWord.length()){
                continue;
            }

            for(int i=97;i<=122;i++){
                if((int)details.beginWord.charAt(details.index)==i){
                    Details details1=new Details(details.beginWord,details.index+1,details.count);
                    queue.add(details1);
                    continue;
                }

                char beingWordChars[]=details.beginWord.toCharArray();
                beingWordChars[details.index]=(char)i;
                String word=new String(beingWordChars);
                if(set.contains(word)&&!map.containsKey(word)){
                    map.put(word,true);
                    Details details1=new Details(word,0,details.count+1);
                    // details1.set.remove(word);
                    queue.add(details1);
                }
            }


        }

        return 0;
    }
}
```

