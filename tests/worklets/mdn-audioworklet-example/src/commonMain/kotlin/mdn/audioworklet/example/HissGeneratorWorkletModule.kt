package mdn.audioworklet.example

import web.audio.AudioWorkletModule

val HissGeneratorWorkletModule = AudioWorkletModule { self ->
    self.registerProcessor("hiss-generator", HissGeneratorProcessor.classRef)
}
