import random
import sys

def create_tree(n):
  parent = []
  parent.append(1)
  children = []
  for i in range(1, n):
      random.seed(len(parent))
      children.append(parent[random.randint(0,len(parent)-1)])
      parent.append(i)
  return children


if __name__ == '__main__':
  n = int(sys.argv[1])
  print(n)
  print(create_tree(n))
  print("1")
  print("1 1")


