/***************************************************************************
* block.cpp    (primitive object plugin)                                   *
*                                                                          *
* The "Block" object defines an axis-aligned box.  Its constructor takes   *
* two vectors, which are taken to be the "min" and "max" corners of the    *
* Block (i.e. the three min coords, and the three max coords).             *
*                                                                          *
* History:                                                                 *
*   10/10/2004  Split off from objects.cpp file.                           *
*                                                                          *
***************************************************************************/
#include "toytracer.h"
#include "util.h"
#include "params.h"

struct Block : public Primitive {
    Block() {}
    Block( const Vec3 &Min, const Vec3 &Max );
    virtual bool Intersect( const Ray &ray, HitInfo &hitinfo ) const;
    virtual bool Inside( const Vec3 &P ) const;
    virtual Interval GetSlab( const Vec3 & ) const;
    virtual int GetSamples( const Vec3 &P, const Vec3 &N, Sample *samples, int n ) const;
    virtual Plugin *ReadString( const string &params );
    virtual string MyName() const { return "block"; }
    Vec3 Min; // Minimum coordinates along each axis.
    Vec3 Max; // Maximum coordinates along each axis.
    };

// Register the new object with the toytracer.  When this module is linked in, the 
// toytracer will automatically recognize the new objects and read them from sdf files.

REGISTER_PLUGIN( Block );

Block::Block( const Vec3 &Min_, const Vec3 &Max_ )
    {
    Min  = Min_;
    Max  = Max_;
    }

Plugin *Block::ReadString( const string &params ) // Read params from string.
    {
    ParamReader get( params );
    Vec3 Vmin, Vmax;
    if( get[MyName()] && get[Vmin] && get[Vmax] ) return new Block( Vmin, Vmax );
    return NULL;
    }

// Determine whether the given point is on or in the object.
bool Block::Inside( const Vec3 &P ) const
    {
    if( P.x < Min.x || P.x > Max.x ) return false;
    if( P.y < Min.y || P.y > Max.y ) return false;
    if( P.z < Min.z || P.z > Max.z ) return false;
    return true;
    }

Interval Block::GetSlab( const Vec3 &v ) const
    {
    const double
        ax = v.x * Min.x,
        ay = v.y * Min.y,
        az = v.z * Min.z,
        bx = v.x * Max.x,
        by = v.y * Max.y,
        bz = v.z * Max.z,
        a  = min( ax, bx ) + min( ay, by ) + min( az, bz ), 
        b  = max( ax, bx ) + max( ay, by ) + max( az, bz ); 
    return Interval( a, b ) / ( v * v );
    }

bool Block::Intersect( const Ray &ray, HitInfo &hitinfo ) const
    {
	/*
	* With this primitive, we need to compute the intersection with each face. 
	*	Each face has a constant x, y, or z
	*	and the other coordinates vary
	*
	* This means we end up wanting to solve the following system (using z face as example)
	*	ray_x + t*direction_x = alpha*max_x + (1-alpha)*min_x
	*	ray_y + t*direction_y = beta*max_y + (1-beta)*min_y
	*	ray_z + t*direction_z = min_z
	*
	*You first solve for t
	*		t = (min_z - ray_z)/direction_z
	*
	* If t > 0 you continue
	*
	*You then solve for alpha which are rearranging is as follows:
	*	alpha = ( ray_x + t*direction_x - min_x )/( max_x - min_x)
	*
	* If 0 <= alpha <= 1 you continue
	*
	*You then solve for beta which after rearranging is as follows
	*	beta = ( ray_y + t*direction_y - min_y )/(max_y - min_y)
	*
	*
	*For the other ones, you follow the same thing but switch x, y, and z
	*/

	//see if it intersects min z face




    return false;  // Remember to change this line!
    }

int Block::GetSamples( const Vec3 &P, const Vec3 &N, Sample *samples, int n ) const
    {
    // To be filled in later.
    return 0;
    }

