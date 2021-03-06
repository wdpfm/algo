package com.wdpfm.line.array;

import java.security.DrbgParameters;

/**
 * 描述:封装数组常用操作 没有申请额外空间→空间复杂度都是O(1)
 */
public class ArrayList<E> {
     private E[] data;//数组
     private int capacity;//容量
     private int size;//存储的元素个数

    public ArrayList(int capacity){
        this.data=(E[])new Object[capacity];
        this.capacity=capacity;
        this.size=0;
    }

    public ArrayList(){
        this(10);
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int getSize(){
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    //指定位置插入元素
    //时间复杂度O(n)
    //最差时间复杂度，循环代码运行最大的次数
    //size=data.length&&index=0
    public void insert(int index,E e){
        //if (size==data.length) throw new IllegalArgumentException("插入失败，数组已满");
        if (index<0||index>size){
            throw new IllegalArgumentException("索引异常");
        }
        if (size==data.length){
            resize(capacity*2);
        }
        //数组后移
        for (int i = size-1; i >= index ; i--) {
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }

    private void resize(int newCapacity){
        //1.创建一个容量为newCapacity的临时数组
        E[] newData=(E[])new Object[newCapacity];
        //2.将原来数组中的元素复制到新数组中
        for (int i = 0; i < size; i++) {
            newData[i]=data[i];
        }
        //3.将新数组覆盖老数据组
        data=newData;
        capacity=newCapacity;
    }

    //头插
    //时间复杂度O(n)
    public void insertFirst(E e){
        insert(0,e);
    }

    //尾插
    //时间复杂度O(1) 均摊时间复杂度O(1)
    public void insertLast(E e){
        insert(size,e);
    }

    //查询操作
    //时间复杂度O(1)
    public E get(int index){
        if(index<0||index>=size)
            throw new IllegalArgumentException("索引异常");
        return data[index];
    }

    //时间复杂度O(n)
    //查找操作 查找元素e所在的索引，如果元素e不存在，则返回-1
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if (data[i]==e)
                return i;
        }
        return -1;
    }

    //判断是否存在
    //时间复杂度O(n)
    public boolean contain(int target){
        for (E num:data){
            if (num.equals(target))
                return true;
        }
        return false;
    }

    //修改操作
    //时间复杂度O(1)
    public void update(int index,E e){
        if(index<0||index>=size)
            throw new IllegalArgumentException("索引异常");
        data[index]=e;
    }

    //删除操作
    //时间复杂度O(n)
    public E delete(int index){
        if(index<0||index>=size)
            throw new IllegalArgumentException("索引异常");
        E result=data[index];
        for (int i = index+1; i < size; i++) {
            data[i-1]=data[i];
        }
        size--;
        //gc清除不用的对象
        data[size]=null;
        //如果size等于总容量的四分之一，则进行缩容
        //因为data.length有可能不断减少，所以有可能小于2
        if(size== data.length/4&&data.length/2!=0){
            resize(data.length/2);
        }
        return result;
    }

    //删除第一个元素
    //时间复杂度O(n)
    public E deleteFirst(){
        return delete(0);
    }

    //删除最后一个元素
    //时间复杂度O(1) 均摊时间复杂度O(1)
    public E deleteLast(){
        return delete(size-1);
    }

    //删除指定元素
    //时间复杂度O(n)
    public void deleteElement(E e){
        int index=find(e);
        if (index!=-1)
            delete(index);
    }

    //获取最后一个元素
    public E getLast(){
        return data[size-1];
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();//用于拼接字符串
        sb.append(String.format("数组大小:%d，数组容量:%d\n",size,capacity));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i!=size-1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
