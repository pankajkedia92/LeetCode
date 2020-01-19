/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    class Pair{
     TreeNode root;
     int heightX;
     int heightY;
     public Pair(TreeNode root,int heightX,int heightY){
       this.root=root;
       this.heightX=heightX;
       this.heightY=heightY;
     }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
         List<List<Integer>>  result=new ArrayList<>();

         if(root==null)
          return result;

         Map<Integer, List<Pair>> map=new HashMap<>();
         int min=0;
         int max=0;

         Queue<Pair> queue=new LinkedList<>();
         queue.add(new Pair(root,0,0));
         while(!queue.isEmpty()){
           Pair current=queue.remove();
           List<Pair> list=map.get(current.heightX);
           if(list==null){
             list=new ArrayList<>();
             map.put(current.heightX,list);
             min=Math.min(min,current.heightX);
             max=Math.max(max,current.heightX);
           }
           list.add(current);

           if(current.root.left!=null){
        queue.add(new Pair(current.root.left,current.heightX-1,current.heightY-1));
           }

            if(current.root.right!=null){
        queue.add(new Pair(current.root.right,current.heightX+1,current.heightY-1));
           }


         }

         for(int i=min;i<=max;i++){
           List<Pair> list=map.get(i);
          Collections.sort(list,(a,b)->{

            int diff=-a.heightY+b.heightY;
            if(diff==0){
              return a.root.val-b.root.val;
            }
            return diff;
          });

           List<Integer> r=new ArrayList<>();
           for(Pair p:list){
             r.add(p.root.val);
           }
           result.add(r);
         }
         return result;
    }
}
