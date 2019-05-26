def fibstr(n):
    s0 = "0"
    s1 = "01"
    def f(n):
        if n == 0: 
               return s0
        if n == 1:
               return s1
        return f(n-1) + f(n-2)
    return f(n)


print(fibstr(24)[:10**5])
