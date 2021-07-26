const express = require('express');
const { isSignedIn, isNotSignedIn } = require('../middlewares');
const User = require('../../schemas/user');

const router = express.Router();


/* PUT user/:id/location page */
router.put('/:id/location', async (req, res, next) => {
    const { location } = req.body;
    try {
        await User.findOneAndUpdate({ userId: req.params.id }, { location });
        res.status(202).json({ updated: true });
    } catch (err) {
        console.log(err);
        return next(err);
    }
});


module.exports = router;