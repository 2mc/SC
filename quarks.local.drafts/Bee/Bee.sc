/* 
IZ 20111001 
Drawing polygons. 

Like Turtle, but with the ability to move to already drawn vertices of a polygon and start other polygons there. ( = Branching?)

Currently only the 'move', turn, left, right, methods have been fully implementd. 
The other methods: jump, next, prev are under development.

*/

Bee {
	var <>poly;			// the polygon on which the Bee is situated
	var <>pace = 10;		// the default step size by which the bee steps
	var <orientation = 0; 	// the current orientation of the bee
	var <>angle;			// the default angle by which the bee turns
	var <>index = -1;		// the index of the node of the Polygon where the bee is sitting
	var <xScale, <yScale;	// proportions of length to move in each direction dependent on orientation

	*new { | poly, pace = 10, orientation = 0, angle, index = -1 |
		^this.newCopyArgs(poly, pace, orientation, angle ?? { pi / 2 }, index).init;
	}

	// analogous to Turtle.draw:
	
	*draw { | patternFunc, window, x, y, orientation = 0, repeats = 1, rate = 0.1 |
		var bee, count = 0, dur, poly;

		window = window ?? { Screen(nil, "Bee", Rect(700, 200, 500, 500)) };
		x = x ?? { window.view.bounds.width / 2 };
		y = y ?? { window.view.bounds.height / 2 };
		poly = Polygon((x@y));
		window add: poly;
		
		bee = this.new(poly, nil, orientation);
		
		if (repeats isKindOf: Integer) { rate = Pn(rate, repeats) };
		rate = rate.asStream;
		{ 
			while { (dur = rate.next).notNil }{
				patternFunc.(bee, count, dur);
				dur.wait;
				count = count + 1;
			}
		}.fork;
		^bee;
	}

	*newWithWindow { | window, x, y, orientation = 0 |
		var poly;
		window = window ?? { Screen(nil, "Bee", Rect(700, 200, 500, 500)) };
		x = x ?? { window.view.bounds.width / 2 };
		y = y ?? { window.view.bounds.height / 2 };
		poly = Polygon((x@y));
		window add: poly;
		^this.new(poly, nil, orientation);
	}

	makeWindow { | name, bounds |
		var screen;
		screen = Screen(nil, name, bounds);
		screen add: poly;
	}

	init {
		poly = poly ?? { Polygon.new };	// provide default polygon
		this.orientation = orientation;	// initialize x and y scale factors from orientation
	}

	pos {
		// calculate the position from the index and the lines of the graph
		// index == -1 means position at origin
		^poly pos: index;	
	}

	orientation_ { | angle |
		orientation = angle;
		// calculate and cache xScale, yScale
		yScale = orientation.sin;
		xScale = orientation.cos;
	}

	move { | length |
		// move forward by pace points in the current direction (angle)
		// and create a line in the polygon to the new position reached
		length = length ? pace;
		poly add: this.pos.translate((length * xScale)@(length * yScale));
		index = index + 1;
	}

	jump { | point |
		// jump to point and create a new polygon there. 
		poly = Polygon(point).graph_(poly.graph);
		index = 0;
	}

	turn { | angleInc |
		// change current orientation by angleInc
		this.orientation = orientation + (angleInc ? angle);
	}
	
	left {
		this.orientation = orientation - angle;
	}
	
	right {
		this.orientation = orientation + angle;
	}

	next { | inc = 1 |
		// go to next point in the poly
		index = (index + inc).wrap(-1, poly.size - 1);
	}
	
	prev { 
		// go to previous point in the poly
		this next: -1;
	}

}

