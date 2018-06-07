public static void main(final String[] args) {
       final List<String> myList = new ArrayList<String>();
       myList.add("A");
       myList.add("B");
       myList.add("C");
       
       if (myList.size() > 0) {
           System.out.println("List is not empty");
       } else {
           System.out.println("List is  empty");
       }
   }
