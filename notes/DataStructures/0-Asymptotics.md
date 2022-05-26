1. 计算order of growth的规则

   - 只考虑the worst case
   - 关注最费时那一步
   - 忽略低次项
   - 去掉常数系数(multiplicative constants)

   

2. Bit Theta Notation
	对于函数F(N)和函数R(N)，如果k1 * R(N) < F(N) < k2 * R(N)对N>=N0成立，则认为**F(N)∈Θ(R(N))**。（其中k1、k2为常数，N0为一个很大的N值）
	
	
	
3. Big O Notation 
    对于函数F(N)和函数R(N)，如果 F(N) <= k2 * R(N)对N>=N0成立，则认为**F(N)∈O(R(N))**。（其中k2为常数，N0为一个很大的N值）。

  

4. Big Omega Notation

   对于函数F(N)和函数R(N)，如果 F(N) >= k2 * R(N)对N>=N0成立，则认为**F(N)∈Ω(R(N))**。



小结：

|         | informal meaning       | Example family   | Example family members                             |
| ------- | ---------------------- | ---------------- | -------------------------------------------------- |
| Θ(f(N)) | equals                 | Θ(N<sup>2</sup>) | N<sup>2</sup>/2, N<sup>2</sup> + N, 2N<sup>2</sup> |
| O(f(N)) | less than or equals    | O(N<sup>2</sup>) | N<sup>2</sup>/2, 2N<sup>2</sup>, logN              |
| Ω(f(N)) | greater than or equals | Ω(N<sup>2</sup>) | N<sup>2</sup>/2, 2N<sup>2</sup>, N<sup>3</sup>     |



5. amortised analysis 均摊分析

   计算每个操作的平均时间复杂度。
