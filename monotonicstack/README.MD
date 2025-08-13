When using a monotonic stack, the decision between increasing and decreasing comes down to what your problem is asking you to find — usually something like:

Next greater element

Next smaller element

Previous greater/smaller

Number of visible elements

Span/range until a condition breaks

Here’s the mental model:

1. Increasing Stack
Definition: Each element in the stack is larger than the one before it (bottom → top).
Used when:

You want to find the next smaller element to the right or left.

You pop when a new element is smaller than the top — meaning you've found a smaller boundary.

Example problems:

Stock span (finding how long prices have been less than or equal to today’s)

Daily Temperatures (find when a hotter day comes — but that’s actually decreasing stack of temps)

Histogram largest rectangle (monotonic increasing stack of bar heights so when you find a smaller bar you pop).

Key rule:

Pop when new_value < stack.top() for strictly increasing

Pop when new_value <= stack.top() for non-strict

2. Decreasing Stack
Definition: Each element in the stack is smaller than the one before it (bottom → top).
Used when:

You want to find the next greater element to the right or left.

You pop when a new element is greater than the top — meaning you've found a taller boundary.

Example problems:

Next Greater Element

Can See Persons Count (pop shorter people when a taller arrives)

Sliding window maximum (monotonic decreasing stack/queue)

Key rule:

Pop when new_value > stack.top() for strictly decreasing

Pop when new_value >= stack.top() for non-strict

3. Choosing the Type
Think of it like this:

You want to find...	Stack type	Pop when...
Next smaller element	Increasing	new_value < stack.top()
Next smaller or equal element	Increasing	new_value <= stack.top()
Next greater element	Decreasing	new_value > stack.top()
Next greater or equal element	Decreasing	new_value >= stack.top()

4. A quick intuition hack
Looking for bigger numbers? Keep a decreasing stack — pop when bigger comes.

Looking for smaller numbers? Keep an increasing stack — pop when smaller comes.

The monotonic stack ensures every element gets pushed/popped at most once → O(n).