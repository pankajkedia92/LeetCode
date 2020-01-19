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
            int count;
            public Details(String beginWord,
                int index,
               int count){
                this.beginWord=beginWord;
                this.index=index;
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
                    queue.add(details1);
                }
            }


        }

        return 0;
    }