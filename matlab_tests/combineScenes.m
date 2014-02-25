A = imread('scene1.ppm');
B = imread('scene2.ppm');
C = A.*0.5 + B.*0.5;
D = A.*(1.0/8.0) + B.*(7.0/8.0);
imwrite(C,'scene1_2_average_blur.ppm');
imwrite(C,'scene1_2_exponential_blur.ppm');