export async function getCancellableResponseOnlyWithOptions(options) {
    return getResponse(options, [])
}

export async function getCancellableResponseWithOptions(a, b, c, options) {
    return getResponse(options, [a, b, c])
}

export async function getCancellableResponseOnlyWithParentOptions(options) {
    return getResponse(options, [])
}

export async function getCancellableResponseWithParentOptions(a, b, c, options) {
    return getResponse(options, [a, b, c])
}

async function getResponse(options, parameters) {
    const signal = options?.signal
    const returnParameters = options?.returnParameters

    return new Promise((resolve, reject) => {
        if (returnParameters) {
            resolve(parameters)
        }

        const timeoutId = setTimeout(() => {
            reject(new Error("REQUEST TIMEOUT ERROR"))
        }, 200);

        signal?.addEventListener("abort", () => {
            clearTimeout(timeoutId)
        })
    }).catch(reason => {
        Promise.globalRejectionHandler?.(reason)
        return Promise.reject(reason)
    })
}
