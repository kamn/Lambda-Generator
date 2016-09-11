# lambda-generator

A nonsensical incremental idling game written in [Clojurescript](https://github.com/clojure/clojurescript) and [re-frame](https://github.com/Day8/re-frame)

## Why
The lure of making Video Games was my gateway drug to programming.
Unfortunately (or fortunately) I never fully explored the game dev programming world.
I only looked at it from afar and occasionally googled for techniques to program games.

One of the most important articles I read was Evolve Your Hierarchy by Mick West.
It was one of the first articles I read that claimed Object Oriented Programming did not work.
This was news to me and I found it amazing there were different ideas about programming.

As I delve into Entity systems I came across several mentions of Functional Reactive Programming(FRP).
I was fascinated but unable to fully use or grasp it.

Though re-frame is not standard FRP, it is close.
So I decided to try it out by making a little clicker game.

## Development Mode

### Compile css:

Compile css file once.

```
lein garden once
```

Automatically recompile css file on change.

```
lein garden auto
```

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Run tests:

```
lein clean
lein doo phantom test once
```

The above command assumes that you have [phantomjs](https://www.npmjs.com/package/phantomjs) installed. However, please note that [doo](https://github.com/bensu/doo) can be configured to run cljs.test in many other JS environments (chrome, ie, safari, opera, slimer, node, rhino, or nashorn).

## Production Build

```
lein clean
lein cljsbuild once min
```
