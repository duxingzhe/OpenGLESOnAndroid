#version 300 es

layout (location = 0) in vec4 aPosition;
layout (location = 1) in vec4 aTextureCoord;

uniform mat4 uTextureMatrix;
out vec2 vTextureCoord;

void main() {
     gl_Position  = aPosition;
     vTextureCoord = (uTextureMatrix * aTextureCoord).xy;
}