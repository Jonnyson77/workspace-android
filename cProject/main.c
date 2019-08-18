#include <stdio.h>

int main()
{
    // 声明变量
   int num;
   int *pi;
   const int *pci;

   // 赋值
   num = 0;
   pi = &num;

   // 解引指针
   *pi = 200;

   printf("Address of num: %d Value : %d \n",&num,num);
   printf("Address of pi: %d Value : %d \n",&pi,pi);
   printf("Address of pi: %p Value : %d \n",&pi,pi);
}