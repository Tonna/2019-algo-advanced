import random
import sys


if __name__ == '__main__':
    n = int(sys.argv[1])

    #setting seed if it was passed, otherwise use default
    if len(sys.argv) > 2:
        s = int(sys.argv[2])
    else:
        s = 7

    random.seed(s)

    #if len(sys.argv) >= 3:
    #    n = 10
    #else:
    #    n = int(sys.argv[2])

    data = list(range(1,n+1))
    #randomize input data
    random.shuffle(data)
    print(n)
    print(" ".join(map(lambda x: str(x), data)))
