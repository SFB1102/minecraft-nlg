package de.saar.coli.minecraft

class ExampleWorlds {
    public final static String MCTIRTG = '''
interpretation string: de.up.ling.irtg.algebra.StringAlgebra
interpretation ref: de.up.ling.irtg.algebra.SetAlgebra
interpretation sem: de.up.ling.irtg.algebra.SubsetAlgebra

// a referent is always the location the current subtree refers to
// Terminalsymbole dürfen nur in einer einzigen Regel auftreten

foreach {loc| location(loc)}:
S! -> s_$loc(RefLoc)
  [string] *("put the block", ?1)
//  [ref] uniq_$loc(?1)
  [ref] ?1
  [sem] ?1 
  //dunion(?1, "at($loc,bxx)")

// RefBlock - refers to a position which is guaranteed to contain a block
foreach {block | it(block)}:
RefBlock -> prefblock_$block
  [string] "the previous block"
  [ref] project_2(intersect_1(at, member_$block(T)))
  [sem] EMPTYSET

RefBlock -> blockloc(RefLoc)
  [string] *("the block", ?1)
  [ref] ?1
  [sem] ?1

// RefLoc - refers to a position which may or may not contain a block

// refer to above of before
// foreach { l1, obj, l2 | top-of(l1,l2) and location(l1) and location(l2) and at(obj,l2)}:
RefLoc -> top(RefBlock)
  [string] *("on top of", ?1)
  [ref] project_1(intersect_2(top-of,?1))
  [sem] ?1
  
RefLoc -> below(RefBlock)
  [string] *("below of", ?1)
  [ref] project_2(intersect_1(top-of,?1))
  [sem] ?1
  
RefLoc -> left(RefBlock)
  [string] *("to the left of", ?1)
  [ref] project_1(intersect_2(left-of,?1))
  [sem] ?1
  
RefLoc -> right(RefBlock)
  [string] *("to the right of", ?1)
  [ref] project_2(intersect_1(left-of,?1))
  [sem] ?1
  
RefLoc -> front(RefBlock)
  [string] *("infront of", ?1)
  [ref] project_1(intersect_2(in-front-of,?1))
  [sem] ?1
  
RefLoc -> front(RefBlock)
  [string] *("behind of", ?1)
  [ref] project_2(intersect_1(in-front-of,?1))
  [sem] ?1
  

RefBlock -> uniqblockdesc(AdjBlock)
 [string] *("the", ?1)
 [ref] project_2(intersect_1(at, size_1(?1)))
 [sem] ?1

AdjBlock -> orangeblock
  [string] "orange block"
  [ref] orange
  [sem] EMPTYSET
  
AdjBlock -> yellowblock
  [string] "yellow block"
  [ref] yellow
  [sem] EMPTYSET

AdjBlock -> blueblock
  [string] "blue block"
  [ref] blue
  [sem] EMPTYSET
'''

    public final static String TESTJSON = '''
 {
    "it": [["b8"]],
    "orange": [
      [
        "b7"
      ]
    ],
    "at": [
      [
        "b7",
        "loc25"
      ]
    ],
    "block": [
      [
        "b7"
      ]
    ],
    "top-of": [
      [
        "loc28",
        "loc25"
      ]
    ],
    "location": [
      [
        "loc25"
      ],
      [
        "loc28"
      ]
    ]
  }
'''

}