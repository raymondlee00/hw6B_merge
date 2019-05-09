/**
  Represent a merge operation for sorted lists,
  as described in README.md
 */
import java.util.ArrayList;

public class Merger {

    ArrayList<String> usersData;

    /**
      Construct an instance from a list of data
      part of which is to be merged. See README
     */
    public Merger( ArrayList<String> list) {
        usersData = list;
    }


    /**
      Merge the sorted sub-lists.
     */
    public void merge(
      // indexes of sub-list boundaries; see README
        int start0  // index of first item in list0
      , int start1  // index of first item in list1
                    // = just past end of list0
      , int end1    // index past end of list1
      ) {
      ArrayList<String> list0 = new ArrayList<String>();
      ArrayList<String> list1 = new ArrayList<String>();
      ArrayList<String> list2 = new ArrayList<String>();
      for(int index = start0; index < start1; index++) {
        list0.add(usersData.get(index));
      }
      for(int index = start1; index < end1; index++) {
        list1.add(usersData.get(index));
      }
      if(list0.isSorted(0, list0.size())) {
        sort(list0);
      } else if(list1.isSorted(0, list1.size())) {
        sort(list1);
      }
      mergeRecursive(list0, list1, new ArrayList<String>());
    }

    public void mergeRecursive(ArrayList<String> list0, ArrayList<String> list1, ArrayList<String> merged) {
      if(list0.size() == 0 && list1.size() == 0) {
        return new ArrayList<String>();
      } else if(list0.size() != 0 && list1.size() == 0) {
        for(String el: list0) {
          merged.add(el);
        }
      } else if(list0.size() == 0 && list1.size() != 0) {
        for(String el: list1) {
          merged.add(el);
        }
      } else if(list0.get(0).compareTo(list1.get(0)) <= 0) {
        merged.add(list0.remove(0));
        return mergeRecursive(list0, list1, list2);
      } else if(list0.get(0).compareTo(list1.get(0)) > 0) {
        merged.add(list1.remove(0));
        return mergeRecursive(list0, list1, list2);
      }
    }


    /**
      @return a string representation of the user's data
     */
    public String toString() {
        return "" + usersData;
    }


    /**
      @return the boolean value of the statement
         "the data in the range are in ascending order"
     */
    public boolean isSorted( int startAt, int endBefore) {
        for( int i = startAt
           ; i < endBefore -1 // stop early, because comparing to next
           ; i++
           )
            if( usersData.get(i).compareTo( usersData.get(i+1)) > 0) {
                System.out.println( "trouble between position " + i
                                  + " and " + (i + 1)
                                  );
                return false;
            }
        return true;
    }
}
