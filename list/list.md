# list的常用方法
-
1. list.addAll(1,list1); //添加一个集合到list集合的指定位置
2. list.contains("xia"); //检查list中是否包含某个元素
3. list.containsAll(list1);   //判断指定集合是否包含另一个集合的所有元素
4. list.get(0);                //返回集合中指定位置的远素
5. list.indexOf();             //返回结合中第一次出现这个元素的位置如果不存在则返回-1
6. list.lastIndexOf();         //返回指定对象最后一次在集合中出现的位置
7. list.isEmpty();             //判断集合是否为空
8. list1.retainAll(list2);     //两个集合求交集
9. list.sort(new Comparator<Integer>()); //排序