/***************************************************************************
* basic_shader.cpp   (shader plugin)                                       *
*                                                                          *
* This file defines a very simple ray tracing shader for the toy tracer.   *
* The job of the shader is to determine the color of the surface, viewed   *
* from the origin of the ray that hit the surface, taking into account the *
* surface material, light sources, and other objects in the scene.         *                          *
*                                                                          *
* History:                                                                 *
*   10/03/2005  Updated for Fall 2005 class.                               *
*   09/29/2004  Updated for Fall 2004 class.                               *
*   04/14/2003  Point lights are now point objects with emission.          *
*   04/01/2003  Initial coding.                                            *
*                                                                          *
***************************************************************************/
#include "toytracer.h"
#include "util.h"
#include "params.h"

struct basic_shader : public Shader {
    basic_shader() {}
   ~basic_shader() {}
    virtual Color Shade( const Scene &, const HitInfo & ) const;
    virtual Plugin *ReadString( const string &params );
    virtual string MyName() const { return "basic_shader"; }
    virtual bool Default() const { return true; }
    };

REGISTER_PLUGIN( basic_shader );

Plugin *basic_shader::ReadString( const string &params ) 
    {
    ParamReader get( params );
    if( get["shader"] && get[MyName()] ) return new basic_shader();
    return NULL;
    }


Color basic_shader::Shade( const Scene &scene, const HitInfo &hit ) const
    {
    // ********* Keep as little or as much of the following code as you wish *******
    // ********* Keep as little or as much of the following code as you wish *******
    // ********* Keep as little or as much of the following code as you wish *******
    Ray ray;
    HitInfo otherhit;
    static const double epsilon = 1.0E-4;
    if( Emitter( hit.object ) ) return hit.object->material->emission;

    Material *mat   = hit.object->material;
    Color  diffuse  = mat->diffuse;
    Color  specular = mat->specular;
    Color  color    = mat->ambient * diffuse;
    Vec3   O = hit.ray.origin;
    Vec3   P = hit.point;
    Vec3   N = hit.normal;
    Vec3   E = Unit( O - P );
    Vec3   R = Unit( ( 2.0 * ( E * N ) ) * N - E );
    Color  r = mat->reflectivity;
    double e = mat->Phong_exp;
    double k = mat->ref_index;

    if( E * N < 0.0 ) N = -N;  // Flip the normal if necessary.

    //******** FILL IN AS NEEDED ****************
    //******** FILL IN AS NEEDED ****************
    //******** FILL IN AS NEEDED ****************

	//get the attentuation
	//	A = 1/(a + b*r + c*r^2)
	double attenuation_a = 0.0;
	double attenuation_b = 0.0;
	double attenuation_c = 0.12;
	double attenuation;
	double lightDistance;
	Vec3 lightVector;
	double diffuseFactor;
	Color diffuseColor = Color();
	Color finalColor;

    for( unsigned i = 0; i < scene.NumLights(); i++ )
        {
        const Object *light = scene.GetLight(i);
        Color emission = light->material->emission;
        AABB box = GetBox( *light );
        Vec3 LightPos( Center( box ) ); 

		//get the attenuation factor for the light
		lightVector = LightPos - P;
		lightDistance = Length(lightVector);
		attenuation = 1/(attenuation_a + attenuation_b*lightDistance + attenuation_c*lightDistance*lightDistance);

		//gets the diffuse component
		diffuseFactor = max(0,lightVector*N);
		diffuseColor = diffuseColor + (attenuation*diffuseFactor)*emission;

        //******** FILL IN AS NEEDED ****************
        //******** FILL IN AS NEEDED ****************
        //******** FILL IN AS NEEDED ****************

        }

	finalColor = color + diffuseColor*diffuse;
    //******** FILL IN AS NEEDED ****************
    //******** FILL IN AS NEEDED ****************
    //******** FILL IN AS NEEDED ****************

	return finalColor; 
    }
