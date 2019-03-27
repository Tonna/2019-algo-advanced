import random
import sys

def degrade_twin_tree(n):
    out = []
    for i in range(1,int(n/2) + 1):
        out.append((i, i + 1))
    out.append((1,int(n/2) + 2))
    for i in range(int(n/2) + 2, n):
        out.append((i, i+ 1))
    return out


if __name__ == '__main__':
    n = int(sys.argv[1])

    #setting seed if it was passed, otherwise use default
    if len(sys.argv) > 3:
        s = int(sys.argv[3])
    else:
        s = 7

    random.seed(s)

    if len(sys.argv) > 2:
        test_cases = int(sys.argv[2])
    else:
        test_cases = random.randint(1,n)

    data = degrade_twin_tree(n)
    #randomize input data order
    #random.shuffle(data)
    print(n, 1)
    print("\n".join(map(lambda x: "%d %d" % x, data)))
    print(test_cases)
    for i in range(0, test_cases):
        print("%d %d" % (random.randint(1,n), random.randint(1,n)))
