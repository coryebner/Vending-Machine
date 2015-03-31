package hardware.Utils;

/**
 * Search
 * 
 * @synopsis
 * 		Library utilities related to various search algorithms for varying datatypes
 * 
 * TODO: Expand class name and functionality + description to include sorting algorithms
 * 
 * @author wwright
 *
 */
class Search {
	
	private Search() {
	}
	
	/**
	 * Array
	 * 
	 * @synopsis
	 * 		Library utilities related to algorithmic array searching/sorting
	 *
	 */
	public static class Array {
		/**
		 * find
		 * 
		 * @synopsis
		 * 		Search for the specified value <double> in the array <double>
		 * 
		 * @param 			array 		<double> array
		 * @param 			value		<double> value to search for in array
		 * @return			
		 * 					success: 	index 	<int> pertaining to array that the value was located in
		 * 					failure:	-1 		<int> if unable to find specified value in array
		 * 
		 * 
		 */
		public static int find(double[] array, double value) {
			for(int i=0; i < array.length; i++) 
				if(array[i] == value)
					return i;

			return -1;
		}
	}
}
