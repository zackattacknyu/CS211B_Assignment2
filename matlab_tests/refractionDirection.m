function [ R ] = refractionDirection( n_eye,n_ref, E, N )
%REFRACTIONDIRECTION Summary of this function goes here
%   Detailed explanation goes here

E = E/norm(E); %normalize the eye vector
N = N/norm(N); %makes sure the normal vector is normalized

ratio = n_eye/n_ref;
cos_theta = dot(N,E);
R_vert = ratio*( N*cos_theta - E );
cos_phi_squared = 1 - ( ratio*ratio * (1-cos_theta*cos_theta));
if(cos_phi_squared < 0.0)
   R = 2*dot(E,N).*N - E;
else
    R = R_vert - N*sqrt(cos_phi_squared);
end


R = R/norm(R);
end

