def make_deep_twin_tree(n):
	out = []
	out.append(1)
	for i in range(2,int(n/2)+1):
		out.append(i)
	out.append(1)
	for i in range(int(n/2)+1,n-1):
		out.append(i+1)
	return out
