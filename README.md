# "How many dota hero names can I chain, such that the ending letter of one hero is the starting letter of the next?" 

This was a random question that popped up, and seemed interesting to code. So here it is!

# Output

* Size: 37

* Heroes: [JAKIRO, OGRE MAGI, INVOKER, RAZOR, RIKI, IO, OMNIKNIGHT, TECHIES, SAND KING, GRIMSTROKE, EARTH SPIRIT, TEMPLAR ASSASSIN, NAGA SIREN, NATURE'S PROPHET, TERRORBLADE, EARTHSHAKER, RUBICK, KEEPER OF THE LIGHT, TIMBERSAW, WARLOCK, KUNKKA, ABADDON, NECROPHOS, SHADOW FIEND, DAZZLE, EMBER SPIRIT, TROLL WARLORD, DOOM, MAGNUS, SKYWRATH MAGE, ENCHANTRESS, SNAPFIRE, ENIGMA, ANTI-MAGE, ELDER TITAN, NYX ASSASSIN, NIGHT STALKER]

# Algorithm details
* Used graph algorithm.
* First I build a graph starting with each hero.
* Then I found depths of all of the above graphs, and the one with the best depth had the answer. Just traverse that graph to get the best-depth-path.
