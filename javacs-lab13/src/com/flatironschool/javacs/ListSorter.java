/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
      
      if( list.size() <= 1 )
      {
         return list;
      }

      List<T> firstHalf = new ArrayList(list.subList(0, list.size() / 2));
      List<T> secondHalf = new ArrayList(list.subList(list.size()/2, list.size()));
    
      firstHalf = mergeSort(firstHalf, comparator);
      secondHalf = mergeSort(secondHalf, comparator);

      //Merge Code
      int firstIndex = 0;
      int secondIndex = 0;

      while( firstIndex < firstHalf.size() && secondIndex < secondHalf.size() ) {
         if( comparator.compare(secondHalf.get(secondIndex), 
            firstHalf.get(firstIndex)) < 0 ) {
            
            firstHalf.add(firstIndex, secondHalf.get(secondIndex) );
            secondIndex++;
         }

         firstIndex++;
      }

      if( secondIndex < secondHalf.size() ) {
         firstHalf.addAll( new ArrayList(secondHalf.subList( secondIndex, 
            secondHalf.size() ) ) );
      }
      
      return firstHalf;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {

      PriorityQueue<T> heap = new PriorityQueue<T>(list.size(), comparator);
      
      for( int index = 0; index < list.size(); index++ ) {
         heap.offer( list.get(index) );
      }
      
      list.clear();
      while( !(heap.isEmpty()) ) {
         list.add(heap.poll());

      }
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
      
      heapSort(list, comparator);
      List<T> topKlist = new ArrayList<T>();

      for( int index = list.size() - k; index < list.size(); index++ ) {
         topKlist.add(list.get(index));
      }
      return topKlist;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
